package dev.mufaro.whispersOfTheVoid.network.payloads

import dev.mufaro.whispersOfTheVoid.data.NetworkingConstants
import dev.mufaro.whispersOfTheVoid.data.PlayerData
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload

@JvmRecord
data class InitialSyncPayload(val playerData: PlayerData) : CustomPayload {
    companion object {
        val ID = CustomPayload.Id<InitialSyncPayload>(NetworkingConstants.INITIAL_SYNC_PACKET_ID);
        val CODEC: PacketCodec<RegistryByteBuf, InitialSyncPayload> = PacketCodec.of(
            { payload, buf ->
                buf.writeInt(payload.playerData.fearLevel);
            },
            { buf ->
                val fearLevel = buf.readInt();
                val playerData = PlayerData();
                playerData.fearLevel = fearLevel;
                InitialSyncPayload(playerData);
            }
        )
    }

    override fun getId(): CustomPayload.Id<*> {
        return ID;
    }
}