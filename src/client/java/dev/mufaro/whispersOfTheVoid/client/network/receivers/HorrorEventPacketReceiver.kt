package dev.mufaro.whispersOfTheVoid.client.network.receivers

import dev.mufaro.whispersOfTheVoid.WhispersOfTheVoid
import dev.mufaro.whispersOfTheVoid.events.mod.HorrorEventRegister
import dev.mufaro.whispersOfTheVoid.network.payloads.HorrorEventPayload
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking

object HorrorEventPacketReceiver {
    fun registerReceiver() {
        ClientPlayNetworking.registerGlobalReceiver(HorrorEventPayload.ID) { payload, context ->
            val eventType = payload.eventType
            val id = payload.id

            WhispersOfTheVoid.Logger.info("(client) receive horror_event packet -> $eventType $id")
        }
    }
}