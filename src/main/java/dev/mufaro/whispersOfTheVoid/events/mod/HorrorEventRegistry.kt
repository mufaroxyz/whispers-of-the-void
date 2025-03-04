package dev.mufaro.whispersOfTheVoid.events.mod

import dev.mufaro.whispersOfTheVoid.WhispersOfTheVoid
import dev.mufaro.whispersOfTheVoid.events.mod.events.BehindYouWhisperEvent

object HorrorEventRegistry {
    private val eventManager = HorrorEventManager()

    fun register() {
        WhispersOfTheVoid.Logger.info("Registering Horror Events")

        eventManager.registerEvent(BehindYouWhisperEvent)
    }

    fun getEventManager(): HorrorEventManager {
        return eventManager
    }
}