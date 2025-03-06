package dev.mufaro.whispersOfTheVoid.events.mod.random

import dev.mufaro.whispersOfTheVoid.data.Constants
import dev.mufaro.whispersOfTheVoid.events.base.*
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayerEntity

interface RandomHorrorEvent : Event {
    val identifier get() = this::class.simpleName
    val type: EventType get() = EventType.NONE
    val category: EventTypeCategory get() = EventTypeCategory.NONE
    val minFearLevel: Constants.FearLevel get() = Constants.FearLevel.NONE
    val weight: Int get() = 0
    fun executeServer(context: ServerEventContext): ReturnForClientExecution<List<ServerPlayerEntity>>
    fun executeServerPost(context: ServerEventContext) {}
    val eventId get() = "${type.name}.${identifier}"
}
