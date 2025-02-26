package dev.mufaro.whispersOfTheVoid.events.mod.events

import dev.mufaro.whispersOfTheVoid.data.Constants
import dev.mufaro.whispersOfTheVoid.events.mod.EventContext
import dev.mufaro.whispersOfTheVoid.events.mod.EventType
import dev.mufaro.whispersOfTheVoid.events.mod.HorrorEvent
import dev.mufaro.whispersOfTheVoid.sounds.CustomSounds
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent

object BehindYouWhisperEvent : HorrorEvent {
    private val sound = CustomSounds.BEHIND_YOU
    override val minFearLevel = Constants.FearLevel.LOW
    override val weight = 10
    override val type = EventType.AMBIENT_SOUND

    override fun execute(context: EventContext) {
        context.player.playSoundToPlayer(sound, SoundCategory.AMBIENT, 1.0f, 1.0f)
    }
}