package dev.mufaro.whispersOfTheVoid.client.net

import dev.mufaro.whispersOfTheVoid.client.WhispersOfTheVoidClient
import dev.mufaro.whispersOfTheVoid.payloads.InitialSyncPayload
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking

object InitialSyncHandler {
    fun registerInitialSyncHandler() {
        ClientPlayNetworking.registerGlobalReceiver(InitialSyncPayload.ID, { payload, context ->
            val playerData = payload.playerData;

            WhispersOfTheVoidClient.ClientLogger.info("Received initial sync payload with fear level: ${playerData.fearLevel}");
        })
    }
}