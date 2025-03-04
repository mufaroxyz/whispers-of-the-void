package dev.mufaro.whispersOfTheVoid.events.fabric

import dev.mufaro.whispersOfTheVoid.WhispersOfTheVoid

object EventRegistry {
    fun register() {
        WhispersOfTheVoid.Logger.info("Registering events")

        ServerPlayConnectionHandler.registerServerPlayConnectionEvents()
        ServerTickHandler.registerServerTickEvents()
        ServerPlayNetworkingHandler.register()
    }
}