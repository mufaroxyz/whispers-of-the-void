package dev.mufaro.whispersOfTheVoid.util

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import java.util.PriorityQueue
import java.util.UUID

object ServerTaskScheduler {
    private data class ScheduledTask(
        val id: UUID = UUID.randomUUID(),
        val ticksRemaining: Int,
        val callback: () -> Unit
    ) : Comparable<ScheduledTask> {
        override fun compareTo(other: ScheduledTask): Int {
            return ticksRemaining.compareTo(other.ticksRemaining)
        }
    }

    private val serverTasks = PriorityQueue<ScheduledTask>()
    private var isInitialized = false

    fun initialize() {
        if (isInitialized) return

        ServerTickEvents.END_SERVER_TICK.register {
            processTaskQueue(serverTasks)
        }

        isInitialized = true
    }

//    fun scheduleClientTask(delayTicks: Int, callback: () -> Unit): UUID {
//        val task = ScheduledTask(ticksRemaining = delayTicks, callback = callback)
//        clientTasks.add(task)
//        return task.id
//    }

    fun scheduleServerTask(delayTicks: Int, callback: () -> Unit): UUID {
        val task = ScheduledTask(ticksRemaining = delayTicks, callback = callback)
        serverTasks.add(task)
        return task.id
    }

    fun cancelTask(taskId: UUID): Boolean {
        val taskToRemove = serverTasks.find { it.id == taskId } ?: return false
        return serverTasks.remove(taskToRemove)
    }

    private fun processTaskQueue(tasksQueue: PriorityQueue<ScheduledTask>) {
        if (tasksQueue.isEmpty()) return

        val tasksToExecute = mutableListOf<ScheduledTask>()
        val tasksToReschedule = mutableListOf<ScheduledTask>()

        while (tasksQueue.isNotEmpty()) {
            val task = tasksQueue.poll()

            if (task.ticksRemaining <= 1) {
                tasksToExecute.add(task)
            } else {
                tasksToReschedule.add(ScheduledTask(
                    id = task.id,
                    ticksRemaining = task.ticksRemaining - 1,
                    callback = task.callback
                ))
            }
        }

        tasksQueue.addAll(tasksToReschedule)

        tasksToExecute.forEach {
            try {
                it.callback()
            } catch (e: Exception) {
                println("Error executing scheduled task: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    // for chaining like .then in typescript lmao
    fun chain(vararg operations: ChainOperation) {
        if (operations.isEmpty()) return

        var currentIndex = 0

        lateinit var executeNextRef: () -> Unit

        executeNextRef = {
            val currentOp = operations[currentIndex]

            // current op
            currentOp.task()

            // schedule next one
            if (++currentIndex < operations.size) {
                val nextOp = operations[currentIndex]
                val scheduleMethod = ::scheduleServerTask
                scheduleMethod(nextOp.delayTicks) {
                    executeNextRef()
                }
            }
        }

        // chain start
        executeNextRef()
    }

    data class ChainOperation(val delayTicks: Int = 0, val task: () -> Unit)
}