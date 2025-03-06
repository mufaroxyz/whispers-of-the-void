package dev.mufaro.whispersOfTheVoid.events.mod.random.events

import dev.mufaro.whispersOfTheVoid.data.Constants
import dev.mufaro.whispersOfTheVoid.events.base.EventType
import dev.mufaro.whispersOfTheVoid.events.base.HorrorEvent
import dev.mufaro.whispersOfTheVoid.events.base.ReturnForClientExecution
import dev.mufaro.whispersOfTheVoid.events.base.ServerEventContext
import dev.mufaro.whispersOfTheVoid.sounds.CustomSoundsRegistry
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.sound.SoundCategory

object BehindYouWhisperEvent : HorrorEvent {
    override val minFearLevel = Constants.FearLevel.LOW
    override val weight = 10
    override val type = EventType.AMBIENT_SOUND

    override fun executeServer(context: ServerEventContext): ReturnForClientExecution<List<ServerPlayerEntity>> {
        if (!context.world.isClient) {
            context.world.playSound(null, context.player.blockPos, CustomSoundsRegistry.BEHIND_YOU, SoundCategory.AMBIENT, 1f, 1f)
        }

        val playerList = context.server.playerManager.playerList

        return ReturnForClientExecution.SuccessPlayerList(playerList)
    }
}