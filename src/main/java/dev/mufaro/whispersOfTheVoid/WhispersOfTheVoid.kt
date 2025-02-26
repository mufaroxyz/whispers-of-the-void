package dev.mufaro.whispersOfTheVoid

import dev.mufaro.whispersOfTheVoid.commands.CommandRegister
import dev.mufaro.whispersOfTheVoid.events.fabric.EventRegister
import dev.mufaro.whispersOfTheVoid.events.mod.HorrorEventRegister
import dev.mufaro.whispersOfTheVoid.payloads.PayloadManager
import dev.mufaro.whispersOfTheVoid.sounds.CustomSounds
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

class WhispersOfTheVoid : ModInitializer {
    companion object {
        const val MOD_ID = "whispers_of_the_void"
        val Logger = LoggerFactory.getLogger(MOD_ID)
    }

    override fun onInitialize() {
        PayloadManager.initialize()
        EventRegister.initialize()
        CommandRegister.initialize()
        CustomSounds.initialize()
        HorrorEventRegister.initialize()

        Logger.info("Whispers of the Void initialized")
    }
}