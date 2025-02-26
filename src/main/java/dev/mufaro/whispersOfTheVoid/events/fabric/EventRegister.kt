package dev.mufaro.whispersOfTheVoid.events.fabric

object EventRegister {
    fun initialize() {
        ServerPlayConnectionHandler.registerServerPlayConnectionEvents()
        ServerTickHandler.registerServerTickEvents()
    }
}