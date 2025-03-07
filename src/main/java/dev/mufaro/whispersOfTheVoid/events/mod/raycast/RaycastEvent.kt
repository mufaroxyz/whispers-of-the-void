package dev.mufaro.whispersOfTheVoid.events.mod.raycast

import dev.mufaro.whispersOfTheVoid.events.base.Event
import dev.mufaro.whispersOfTheVoid.events.base.EventTypeCategory
import net.minecraft.entity.Entity
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayerEntity
import java.util.UUID

data class RaycastEventContext(
    val player: ServerPlayerEntity,
    val server: MinecraftServer,
    val entityUUID: UUID
)

interface RaycastEvent<T> : Event {
    private val identifier get() = this::class.simpleName
    val type get() = EventTypeCategory.RAYCAST
    val targetedEntity: Class<T>
    fun isTargetEntity(context: RaycastEventContext): Boolean
    fun onEntityRaycasted(context: RaycastEventContext)
    val eventId get() = "${type.name}.${identifier}"
}