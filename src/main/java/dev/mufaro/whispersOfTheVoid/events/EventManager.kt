package dev.mufaro.whispersOfTheVoid.events

object EventManager {
    fun initialize() {
        ServerPlayConnectionHandler.registerServerPlayConnectionEvents()
    }
}