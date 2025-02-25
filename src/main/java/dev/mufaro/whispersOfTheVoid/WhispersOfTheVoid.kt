package dev.mufaro.whispersOfTheVoid

import dev.mufaro.whispersOfTheVoid.commands.CommandRegister
import dev.mufaro.whispersOfTheVoid.events.EventManager
import dev.mufaro.whispersOfTheVoid.payloads.PayloadManager
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

class WhispersOfTheVoid : ModInitializer {
    companion object {
        const val MOD_ID = "whispers_of_the_void"
        val Logger = LoggerFactory.getLogger(MOD_ID)
    }

    override fun onInitialize() {
        PayloadManager.initialize()
        EventManager.initialize()
        CommandRegister.initialize()

        Logger.info("Whispers of the Void initialized")
    }
}