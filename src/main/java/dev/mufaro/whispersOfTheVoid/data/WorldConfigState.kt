    package dev.mufaro.whispersOfTheVoid.data

import dev.mufaro.whispersOfTheVoid.WhispersOfTheVoid
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryWrapper
import net.minecraft.server.MinecraftServer
import net.minecraft.world.PersistentState
import net.minecraft.world.World

object WorldConfigState : PersistentState() {
    var worldConfig = WorldConfig()

    private val type: Type<WorldConfigState> = Type(
        this::createNew,
        this::createFromNbt,
        null
    )

    override fun writeNbt(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup?): NbtCompound {
        nbt.putBoolean("countTicks", worldConfig.countTicks)

        return nbt
    }

    fun getServerState(server: MinecraftServer): WorldConfigState {
        val world = server.getWorld(World.OVERWORLD)

        return world?.persistentStateManager?.getOrCreate(type, "${WhispersOfTheVoid.MOD_ID}_world_config")
            ?: run {
                WhispersOfTheVoid.Logger.error("Failed to get persistent state manager for server");
                this;
            }.apply { markDirty() }
    }

    fun createFromNbt(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup?): WorldConfigState {
        val state = WorldConfigState
        state.worldConfig.countTicks = nbt.getBoolean("countTicks")

        return state
    }

    fun createNew(): WorldConfigState {
        val state = WorldConfigState
        state.worldConfig = WorldConfig()
        return state
    }
}