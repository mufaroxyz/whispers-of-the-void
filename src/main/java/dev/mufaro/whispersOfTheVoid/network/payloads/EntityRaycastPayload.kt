package dev.mufaro.whispersOfTheVoid.network.payloads

import dev.mufaro.whispersOfTheVoid.data.NetworkingConstants
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload

class EntityRaycastPayload(val entityNetId: Int) : CustomPayload {
    companion object {
        val ID = CustomPayload.Id<EntityRaycastPayload>(NetworkingConstants.ENTITY_RAYCAST_PACKET_ID)
        val CODEC: PacketCodec<RegistryByteBuf, EntityRaycastPayload> = PacketCodec.of(
            { payload, buf ->
                buf.writeInt(payload.entityNetId)
            },
            { buf ->
                val entityNetId = buf.readInt()
                EntityRaycastPayload(entityNetId)
            }
        )
    }

    override fun getId(): CustomPayload.Id<*> {
        return ID
    }
}