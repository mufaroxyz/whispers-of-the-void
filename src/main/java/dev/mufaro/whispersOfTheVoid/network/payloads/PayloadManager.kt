package dev.mufaro.whispersOfTheVoid.network.payloads

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry

object PayloadManager {
    fun initialize() {
        PayloadTypeRegistry.playS2C().register(InitialSyncPayload.ID, InitialSyncPayload.CODEC)
        PayloadTypeRegistry.playS2C().register(HorrorEventPayload.ID, HorrorEventPayload.CODEC)
    }
}