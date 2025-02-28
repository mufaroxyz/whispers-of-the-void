package dev.mufaro.whispersOfTheVoid.client.network.receivers

import dev.mufaro.whispersOfTheVoid.WhispersOfTheVoid
import dev.mufaro.whispersOfTheVoid.client.WhispersOfTheVoidClient
import dev.mufaro.whispersOfTheVoid.network.payloads.InitialSyncPayload
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking

object InitialSyncPacketReceiver {
    fun registerReceiver() {
        ClientPlayNetworking.registerGlobalReceiver(InitialSyncPayload.ID, { payload, context ->
            val playerData = payload.playerData;
            WhispersOfTheVoidClient.clientData.playerData = playerData;

            WhispersOfTheVoid.Logger.info("Received initial sync payload with fear level: ${playerData.fearLevel}");
        })
    }
}