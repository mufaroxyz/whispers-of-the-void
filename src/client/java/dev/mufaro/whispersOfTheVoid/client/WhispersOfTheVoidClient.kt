package dev.mufaro.whispersOfTheVoid.client

import dev.mufaro.whispersOfTheVoid.WhispersOfTheVoid.Companion.MOD_ID
import dev.mufaro.whispersOfTheVoid.client.net.NetReceiverManager
import net.fabricmc.api.ClientModInitializer
import org.slf4j.LoggerFactory

class WhispersOfTheVoidClient : ClientModInitializer {
    companion object {
        val ClientLogger = LoggerFactory.getLogger(MOD_ID);
    }

    override fun onInitializeClient() {
        NetReceiverManager.initialize()
    }
}
