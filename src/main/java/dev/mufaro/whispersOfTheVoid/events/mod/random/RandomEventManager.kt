package dev.mufaro.whispersOfTheVoid.events.mod.random

import dev.mufaro.whispersOfTheVoid.events.base.EventType
import dev.mufaro.whispersOfTheVoid.events.base.ReturnForClientExecution
import dev.mufaro.whispersOfTheVoid.events.base.ServerEventContext
import dev.mufaro.whispersOfTheVoid.network.packets.HorrorEventPacket

class RandomEventManager {
    private val events = mutableMapOf<EventType, MutableList<RandomHorrorEvent>>()

    fun getEvents(): Map<EventType, List<RandomHorrorEvent>> {
        return events
    }

    fun registerEvent(event: RandomHorrorEvent) {
        events.getOrPut(event.type) { mutableListOf() }.add(event)
    }

    private fun getEvent(category: EventType, identifier: String): RandomHorrorEvent {
        return events[category]?.find { it.identifier == identifier } ?: throw IllegalArgumentException("Event $identifier not found")
    }

    fun triggerEvent(context: ServerEventContext, category: EventType, identifier: String) {
        if (category == EventType.NONE) return

        val event = getEvent(category, identifier)

        val targetPlayers = event.executeServer(context)
        if (targetPlayers is ReturnForClientExecution.SuccessPlayerList) {
            HorrorEventPacket.sendToPlayers(targetPlayers.value, event.eventId)
        }

        // post-client execution just in case if it's needed
        event.executeServerPost(context)
    }

    // todo: Move this function to the new event system & implement proper fear level ranking
//    fun triggerRandomEvent(context: ServerEventContext, ofType: EventType?) {
//        if (events.isEmpty() || (ofType != null && ofType == EventType.NONE)) return
//
//        val possibleEvents =
//            if (ofType == null)
//                events.values
//                    .flatten()
//                    .filter { it.minFearLevel <= context.fearLevel }
//            else events[ofType] ?: return
//
//        val totalWeight = possibleEvents.sumOf { it.weight }
//        if (totalWeight <= 0) return
//
//        var random = context.world.random.nextInt(totalWeight)
//        for (event in possibleEvents) {
//            random -= event.weight
//            if (random < 0) {
//                event.identifier?.let { triggerEvent(context, event.type, it) }
//                return
//            }
//        }
//    }
}