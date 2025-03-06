package dev.mufaro.whispersOfTheVoid.events.mod.random

import dev.mufaro.whispersOfTheVoid.events.base.*
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayerEntity

interface RandomHorrorEvent : Event {
    private val identifier get() = this::class.simpleName
    val type: EventType get() = EventType.NONE
    val category: EventTypeCategory get() = EventTypeCategory.NONE
    val minFearLevel: Int get() = 0
    val weight: Int get() = 0
    fun executeServer(context: _ServerEventContext): ReturnForClientExecution<List<ServerPlayerEntity>>
    fun executeServerPost(context: _ServerEventContext) {}
    val eventId get() = "${category.name}.${type.name}.${identifier}"
}
