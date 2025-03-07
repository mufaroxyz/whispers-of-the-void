package dev.mufaro.whispersOfTheVoid.events.base

import dev.mufaro.whispersOfTheVoid.data.Constants
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

enum class EventTypeCategory {
    RANDOM,
    RAYCAST,
    NONE
}

enum class EventType {
    ENTITY_SPAWN,
    AMBIENT_SOUND,
    SCREEN_EFFECT,
    NONE
}

