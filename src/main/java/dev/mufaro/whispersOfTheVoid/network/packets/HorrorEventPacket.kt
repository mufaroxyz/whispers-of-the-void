package dev.mufaro.whispersOfTheVoid.network.packets

import dev.mufaro.whispersOfTheVoid.network.payloads.HorrorEventPayload
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.server.network.ServerPlayerEntity

object HorrorEventPacket {
    fun sendToPlayers(players: Collection<ServerPlayerEntity>, eventId: String) {
        val buffer = PacketByteBufs.create()
        buffer.writeString(eventId)
        players.forEach { player ->
            ServerPlayNetworking.send(player, HorrorEventPayload(eventId))
        }
    }
}