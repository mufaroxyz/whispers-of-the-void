package dev.mufaro.whispersOfTheVoid.network.payloads

import dev.mufaro.whispersOfTheVoid.data.NetworkingConstants
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload

@JvmRecord
data class HorrorEventPayload(val eventId: String) : CustomPayload {
    companion object {
        val ID = CustomPayload.Id<HorrorEventPayload>(NetworkingConstants.HORROR_EVENT_PACKET_ID)
        val CODEC: PacketCodec<RegistryByteBuf, HorrorEventPayload> = PacketCodec.of(
            { payload, buf ->
                buf.writeString(payload.eventId)
            },
            { buf ->
                val eventId = buf.readString()
                HorrorEventPayload(eventId)
            }
        )
    }

    override fun getId(): CustomPayload.Id<*> {
        return ID
    }
}