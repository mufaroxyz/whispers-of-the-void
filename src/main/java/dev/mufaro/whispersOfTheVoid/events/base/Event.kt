package dev.mufaro.whispersOfTheVoid.events.base

interface Event {
    fun canTrigger() = true
}