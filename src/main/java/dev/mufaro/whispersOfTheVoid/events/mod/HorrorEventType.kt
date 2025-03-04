package dev.mufaro.whispersOfTheVoid.events.mod

import dev.mufaro.whispersOfTheVoid.data.Constants
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

enum class EventType {
    ENTITY_SPAWN,
    AMBIENT_SOUND,
    SCREEN_EFFECT,
    NONE
}

interface HorrorEvent {
    val identifier get() = this::class.simpleName
    val type: EventType
    val minFearLevel: Constants.FearLevel
    val weight: Int
    fun executeServer(context: ServerEventContext): ReturnForClientExecution<List<ServerPlayerEntity>>
    fun executeServerPost(context: ServerEventContext) {}
    val eventId: String
        get() = "${type.name}.$identifier"
}

data class ServerEventContext(
    val world: World,
    val server: MinecraftServer,
    val pos: BlockPos,
    val player: PlayerEntity,
    val fearLevel: Constants.FearLevel
)

sealed class ReturnForClientExecution<out T> {
    data class SuccessPlayerList<out T>(val value: T) : ReturnForClientExecution<T>()
    data class No(val value: Int = 0) : ReturnForClientExecution<Nothing>()
}