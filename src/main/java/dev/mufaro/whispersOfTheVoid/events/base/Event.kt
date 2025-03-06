package dev.mufaro.whispersOfTheVoid.events.base

interface Event {
    fun canTrigger(): Boolean
    fun execute()
    fun getIdentifier(): String
}