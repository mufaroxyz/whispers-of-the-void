package dev.mufaro.whispersOfTheVoid.client

import dev.mufaro.whispersOfTheVoid.WhispersOfTheVoid.Companion.MOD_ID
import dev.mufaro.whispersOfTheVoid.client.data.ClientData
import dev.mufaro.whispersOfTheVoid.client.events.ClientHorrorEventHandler
import dev.mufaro.whispersOfTheVoid.client.events.ClientHorrorEvents
import dev.mufaro.whispersOfTheVoid.client.network.NetReceiverManager
import net.fabricmc.api.ClientModInitializer
import org.slf4j.LoggerFactory

class WhispersOfTheVoidClient : ClientModInitializer {
    companion object {
        val clientData = ClientData()
    }

    override fun onInitializeClient() {
        NetReceiverManager.initialize()
        ClientHorrorEvents.initialize()
    }
}
