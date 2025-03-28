package dev.mufaro.whispersOfTheVoid.events.internal

import dev.mufaro.whispersOfTheVoid.data.PlayerFearState
import dev.mufaro.whispersOfTheVoid.network.payloads.InitialSyncPayload
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking

object ServerPlayConnectionHandler {
    fun registerServerPlayConnectionEvents() {
        ServerPlayConnectionEvents.JOIN.register { handler, _, server ->
            val playerState = PlayerFearState.getPlayerState(handler.player)
            val data = PacketByteBufs.create()
            data.writeInt(playerState.fearLevel)
            server.execute {
                ServerPlayNetworking.send(handler.player, InitialSyncPayload(playerState))
            }
        }
    }
}