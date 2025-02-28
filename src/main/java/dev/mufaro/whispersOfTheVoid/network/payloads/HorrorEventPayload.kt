package dev.mufaro.whispersOfTheVoid.network.payloads

import dev.mufaro.whispersOfTheVoid.data.NetworkingConstants
import dev.mufaro.whispersOfTheVoid.events.mod.EventType
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload
import net.minecraft.util.Identifier

@JvmRecord
data class HorrorEventPayload(val id: String, val eventType: EventType) : CustomPayload {
    companion object {
        val ID = CustomPayload.Id<HorrorEventPayload>(NetworkingConstants.HORROR_EVENT_PACKET_ID);
        val CODEC: PacketCodec<RegistryByteBuf, HorrorEventPayload> = PacketCodec.of(
            { payload, buf ->
                buf.writeString(payload.id);
                buf.writeString(payload.eventType.name);
            },
            { buf ->
                val id = buf.readString();
                val eventType = EventType.valueOf(buf.readString());
                HorrorEventPayload(id, eventType);
            }
        )
    }

    override fun getId(): CustomPayload.Id<*> {
        return ID;
    }
}