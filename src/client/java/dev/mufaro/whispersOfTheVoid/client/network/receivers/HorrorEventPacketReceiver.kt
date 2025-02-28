package dev.mufaro.whispersOfTheVoid.client.network.receivers

import dev.mufaro.whispersOfTheVoid.WhispersOfTheVoid
import dev.mufaro.whispersOfTheVoid.client.data.ClientEventContext
import dev.mufaro.whispersOfTheVoid.client.events.ClientHorrorEventHandler
import dev.mufaro.whispersOfTheVoid.client.events.ClientHorrorEvents
import dev.mufaro.whispersOfTheVoid.network.payloads.HorrorEventPayload
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking

object HorrorEventPacketReceiver {
    fun registerReceiver() {
        ClientPlayNetworking.registerGlobalReceiver(HorrorEventPayload.ID) { payload, context ->
            val eventId = payload.eventId

            WhispersOfTheVoid.Logger.info("receive horror_event packet -> $eventId")

            val client = context.client()

            ClientHorrorEventHandler.executeClientEvent(ClientEventContext(client), eventId)
        }
    }
}