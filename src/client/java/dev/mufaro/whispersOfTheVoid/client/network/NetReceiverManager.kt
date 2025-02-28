package dev.mufaro.whispersOfTheVoid.client.network

import dev.mufaro.whispersOfTheVoid.client.network.receivers.HorrorEventPacketReceiver
import dev.mufaro.whispersOfTheVoid.client.network.receivers.InitialSyncPacketReceiver

object NetReceiverManager {
    fun initialize() {
        InitialSyncPacketReceiver.registerReceiver()
        HorrorEventPacketReceiver.registerReceiver()
    }
}