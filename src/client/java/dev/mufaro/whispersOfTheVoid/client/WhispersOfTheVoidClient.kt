package dev.mufaro.whispersOfTheVoid.client

import dev.mufaro.whispersOfTheVoid.client.data.ClientData
import dev.mufaro.whispersOfTheVoid.client.entity.EntityRegistry
import dev.mufaro.whispersOfTheVoid.client.events.ClientHorrorEvents
import dev.mufaro.whispersOfTheVoid.client.network.NetReceiverManager
import net.fabricmc.api.ClientModInitializer

class WhispersOfTheVoidClient : ClientModInitializer {
    companion object {
        val clientData = ClientData()
    }

    override fun onInitializeClient() {
        NetReceiverManager.initialize()
        ClientHorrorEvents.initialize()
        EntityRegistry.register()
    }
}
