package dev.mufaro.whispersOfTheVoid.events.internal

import dev.mufaro.whispersOfTheVoid.network.payloads.EntityRaycastPayload
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking

object ServerPlayNetworkingHandler {
    fun register() {
        ServerPlayNetworking.registerGlobalReceiver(EntityRaycastPayload.ID, { payload, player ->
            val entityUUID = payload.entityUUID
            println("Received entity raycast packet with UUID: $entityUUID sent by player: ${player.player().name}")
        })
    }
}