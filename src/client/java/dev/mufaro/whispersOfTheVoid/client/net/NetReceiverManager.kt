package dev.mufaro.whispersOfTheVoid.client.net

object NetReceiverManager {
    fun initialize() {
        InitialSyncHandler.registerInitialSyncHandler()
    }
}