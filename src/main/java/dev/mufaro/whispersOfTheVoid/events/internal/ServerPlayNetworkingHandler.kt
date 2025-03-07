package dev.mufaro.whispersOfTheVoid.events.internal

import dev.mufaro.whispersOfTheVoid.events.base.EventRegistry
import dev.mufaro.whispersOfTheVoid.events.mod.raycast.RaycastEventContext
import dev.mufaro.whispersOfTheVoid.network.payloads.EntityRaycastPayload
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import java.util.UUID

object ServerPlayNetworkingHandler {
    fun register() {
        ServerPlayNetworking.registerGlobalReceiver(EntityRaycastPayload.ID, { payload, player ->
            val entityUUID = payload.entityUUID
            println("Received entity raycast packet with UUID: $entityUUID sent by player: ${player.player().name}")
            var raycastEventContext = RaycastEventContext(
                player.player(),
                player.player().server,
                UUID.fromString(entityUUID)
            )
            EventRegistry.raycastEventManager.handleEntityRaycast(raycastEventContext)
        })
    }
}