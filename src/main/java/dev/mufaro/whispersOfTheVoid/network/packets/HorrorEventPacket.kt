package dev.mufaro.whispersOfTheVoid.network.packets

import dev.mufaro.whispersOfTheVoid.events.mod.EventType
import dev.mufaro.whispersOfTheVoid.network.payloads.HorrorEventPayload
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.server.network.ServerPlayerEntity

object HorrorEventPacket {
    fun sendToPlayers(players: Collection<ServerPlayerEntity>, eventType: EventType, id: String) {
        val buffer = PacketByteBufs.create()
        buffer.writeString(id)
        buffer.writeString(eventType.name)
        players.forEach { player ->
            ServerPlayNetworking.send(player, HorrorEventPayload(id, eventType))
        }
    }
}