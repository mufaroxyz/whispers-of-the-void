package dev.mufaro.whispersOfTheVoid.events.base

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.MinecraftServer

data class ServerEventContext(
    val server: MinecraftServer,
    val player: PlayerEntity
)