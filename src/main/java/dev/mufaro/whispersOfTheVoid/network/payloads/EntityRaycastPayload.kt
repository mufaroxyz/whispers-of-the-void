package dev.mufaro.whispersOfTheVoid.network.payloads

import dev.mufaro.whispersOfTheVoid.data.NetworkingConstants
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload

class EntityRaycastPayload(val entityUUID: String) : CustomPayload {
    companion object {
        val ID = CustomPayload.Id<EntityRaycastPayload>(NetworkingConstants.ENTITY_RAYCAST_PACKET_ID)
        val CODEC: PacketCodec<RegistryByteBuf, EntityRaycastPayload> = PacketCodec.of(
            { payload, buf ->
                buf.writeString(payload.entityUUID)
            },
            { buf ->
                val entityUUID = buf.readString()
                EntityRaycastPayload(entityUUID)
            }
        )
    }

    override fun getId(): CustomPayload.Id<*> {
        return ID
    }
}