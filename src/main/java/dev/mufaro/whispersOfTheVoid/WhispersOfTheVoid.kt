package dev.mufaro.whispersOfTheVoid

import dev.mufaro.whispersOfTheVoid.commands.CommandRegistry
import dev.mufaro.whispersOfTheVoid.entity.EntityRegistry
import dev.mufaro.whispersOfTheVoid.entity.voidwraith.VoidwraithEntity
import dev.mufaro.whispersOfTheVoid.events.fabric.EventRegistry
import dev.mufaro.whispersOfTheVoid.events.mod.HorrorEventRegistry
import dev.mufaro.whispersOfTheVoid.network.PayloadRegistry
import dev.mufaro.whispersOfTheVoid.sounds.CustomSoundsRegistry
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class WhispersOfTheVoid : ModInitializer {
    companion object {
        const val MOD_ID = "whispers-of-the-void"
        val Logger: Logger = LoggerFactory.getLogger(MOD_ID)
    }

    override fun onInitialize() {
        PayloadRegistry.register()
        EventRegistry.register()
        CommandRegistry.register()
        CustomSoundsRegistry.register()
        HorrorEventRegistry.register()
        EntityRegistry

        FabricDefaultAttributeRegistry.register(EntityRegistry.Voidwraith, VoidwraithEntity.createAttributes())

        Logger.info("Whispers of the Void initialized")
    }
}