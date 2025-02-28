package dev.mufaro.whispersOfTheVoid.client.data

import net.minecraft.client.MinecraftClient

data class ClientEventContext(
    val client: MinecraftClient,
)

interface ClientHorrorEvent {
    fun execute(ctx: ClientEventContext)
}