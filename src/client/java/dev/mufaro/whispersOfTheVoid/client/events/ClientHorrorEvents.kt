package dev.mufaro.whispersOfTheVoid.client.events

import dev.mufaro.whispersOfTheVoid.client.events.executors.BehindYouWhisperClientEvent
import dev.mufaro.whispersOfTheVoid.events.mod.events.BehindYouWhisperEvent

object ClientHorrorEvents {
    fun initialize() {
        ClientHorrorEventHandler.registerClientExecutor(BehindYouWhisperEvent.eventId, BehindYouWhisperClientEvent()::execute)
    }
}