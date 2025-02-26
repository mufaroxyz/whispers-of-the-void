package dev.mufaro.whispersOfTheVoid.events.mod

import dev.mufaro.whispersOfTheVoid.data.Constants
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

enum class EventType {
    ENTITY_SPAWN,
    AMBIENT_SOUND,
    SCREEN_EFFECT
}

interface HorrorEvent {
    val identifier get() = this::class.simpleName
    val type: EventType
    val minFearLevel: Constants.FearLevel
    val weight: Int
    fun execute(context: EventContext)
}

data class EventContext(
    val world: World,
    val pos: BlockPos,
    val player: PlayerEntity,
    val fearLevel: Constants.FearLevel
)