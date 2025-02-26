    package dev.mufaro.whispersOfTheVoid.data

import dev.mufaro.whispersOfTheVoid.WhispersOfTheVoid
import net.minecraft.entity.LivingEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryWrapper
import net.minecraft.server.MinecraftServer
import net.minecraft.world.PersistentState
import net.minecraft.world.World
import java.util.UUID

object PlayerFearState : PersistentState() {
    private var fearLevel = 0
    var players = mutableMapOf<UUID, PlayerData>()

    private val type: Type<PlayerFearState> = Type(
        this::createNew,
        this::createFromNbt,
        null
    )

    override fun writeNbt(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup?): NbtCompound {
//        nbt.putInt("fearLevel", fearLevel)

        val playersNbt = NbtCompound();
        players.forEach { (uuid, playerData) ->
            val playerNbt = NbtCompound();
            playerNbt.putInt("fearLevel", playerData.fearLevel);
            playersNbt.put(uuid.toString(), playerNbt)
        };
        nbt.put("players", playersNbt)

        return nbt
    }

    fun getServerState(server: MinecraftServer): PlayerFearState {
        val world = server.getWorld(World.OVERWORLD)

        return world?.persistentStateManager?.getOrCreate(type, "${WhispersOfTheVoid.MOD_ID}_player_fear")
            ?: run {
                WhispersOfTheVoid.Logger.error("Failed to get persistent state manager for server");
                this;
            }.apply { markDirty() }
    }

    fun getPlayerState(player: LivingEntity): PlayerData {
        val serverState = player.world.server?.let { getServerState(it) }
        val playerState = serverState?.players?.computeIfAbsent(player.uuid) { PlayerData() } ?: PlayerData()
        return playerState
    }

    fun createFromNbt(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup?): PlayerFearState {
        val state = PlayerFearState
//        state.fearLevel = nbt.getInt("fearLevel");

        val playersNbt = nbt.getCompound("players")
        playersNbt.keys.forEach { uuid ->
            val playerNbt = playersNbt.getCompound(uuid)
            val playerData = PlayerData()
            playerData.fearLevel = playerNbt.getInt("fearLevel")
            state.players[UUID.fromString(uuid)] = playerData
        }

        return state
    }

    fun createNew(): PlayerFearState {
        val state = PlayerFearState
        state.fearLevel = 0
        state.players = mutableMapOf()
        return state
    }
}