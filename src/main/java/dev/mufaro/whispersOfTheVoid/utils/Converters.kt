package dev.mufaro.whispersOfTheVoid.utils

import dev.mufaro.whispersOfTheVoid.data.Constants
import dev.mufaro.whispersOfTheVoid.events.mod.EventType

fun numericFearToLevel(days: Int): Constants.FearLevel {
    return when {
        days < Constants.FEAR_THRESHOLD_LOW -> Constants.FearLevel.NONE
        days < Constants.FEAR_THRESHOLD_MEDIUM -> Constants.FearLevel.LOW
        days < Constants.FEAR_THRESHOLD_HIGH -> Constants.FearLevel.MEDIUM
        days < Constants.FEAR_THRESHOLD_PANIC -> Constants.FearLevel.HIGH
        else -> Constants.FearLevel.PANIC
    }
}

fun castStringToEventType(str: String): EventType {
    return when (str) {
        "AMBIENT_SOUND" -> EventType.AMBIENT_SOUND
        "ENTITY_SPAWN" -> EventType.ENTITY_SPAWN
        "SCREE_EFFECT" -> EventType.SCREEN_EFFECT
        else -> EventType.NONE
    }
}