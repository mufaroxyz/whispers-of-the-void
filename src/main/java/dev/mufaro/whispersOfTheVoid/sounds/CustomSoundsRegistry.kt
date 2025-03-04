package dev.mufaro.whispersOfTheVoid.sounds

import dev.mufaro.whispersOfTheVoid.WhispersOfTheVoid
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier

object CustomSoundsRegistry {
    var BEHIND_YOU = registerSound("behind_you")

    private fun registerSound(id: String): SoundEvent {
        val identifier = Identifier.of(WhispersOfTheVoid.MOD_ID, id)
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier))
    }

    fun register() {
        WhispersOfTheVoid.Logger.info("Registering sounds")
    }
}