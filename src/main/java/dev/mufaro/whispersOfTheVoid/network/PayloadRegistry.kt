package dev.mufaro.whispersOfTheVoid.network

import dev.mufaro.whispersOfTheVoid.network.payloads.HorrorEventPayload
import dev.mufaro.whispersOfTheVoid.network.payloads.InitialSyncPayload
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry

object PayloadRegistry {
    fun register() {
        PayloadTypeRegistry.playS2C().register(InitialSyncPayload.ID, InitialSyncPayload.CODEC)
        PayloadTypeRegistry.playS2C().register(HorrorEventPayload.ID, HorrorEventPayload.CODEC)
    }
}