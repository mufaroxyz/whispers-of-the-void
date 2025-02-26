package dev.mufaro.whispersOfTheVoid.events.mod

import dev.mufaro.whispersOfTheVoid.events.mod.events.BehindYouWhisperEvent

object HorrorEventRegister {
    private val eventManager = HorrorEventManager()

    fun initialize() {
        eventManager.registerEvent(BehindYouWhisperEvent)
    }

    fun getEventManager(): HorrorEventManager {
        return eventManager
    }
}