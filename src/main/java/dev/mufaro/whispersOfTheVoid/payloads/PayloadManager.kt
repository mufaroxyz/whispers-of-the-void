package dev.mufaro.whispersOfTheVoid.payloads

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry

object PayloadManager {
    fun initialize() {
        PayloadTypeRegistry.playS2C().register(InitialSyncPayload.ID, InitialSyncPayload.CODEC)
    }
}