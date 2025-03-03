package dev.mufaro.whispersOfTheVoid

import dev.mufaro.whispersOfTheVoid.commands.CommandRegister
import dev.mufaro.whispersOfTheVoid.entity.EntityRegistry
import dev.mufaro.whispersOfTheVoid.entity.voidwraith.VoidwraithEntity
import dev.mufaro.whispersOfTheVoid.events.fabric.EventRegister
import dev.mufaro.whispersOfTheVoid.events.mod.HorrorEventRegister
import dev.mufaro.whispersOfTheVoid.network.payloads.PayloadManager
import dev.mufaro.whispersOfTheVoid.sounds.CustomSounds
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import org.slf4j.LoggerFactory

class WhispersOfTheVoid : ModInitializer {
    companion object {
        const val MOD_ID = "whispers-of-the-void"
        val Logger = LoggerFactory.getLogger(MOD_ID)
    }

    override fun onInitialize() {
        PayloadManager.initialize()
        EventRegister.initialize()
        CommandRegister.initialize()
        CustomSounds.initialize()
        HorrorEventRegister.initialize()

        FabricDefaultAttributeRegistry.register(EntityRegistry.Voidwraith, VoidwraithEntity.createAttributes())

        Logger.info("Whispers of the Void initialized")
    }
}