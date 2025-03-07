package dev.mufaro.whispersOfTheVoid.events.mod.raycast

import net.minecraft.entity.Entity
import net.minecraft.server.MinecraftServer
import java.util.UUID

class RaycastEventManager {
    private val events = mutableMapOf<String, RaycastEvent<*>>()

    fun <T : Entity> register(event: RaycastEvent<T>) {
        events[event.eventId] = event
    }

    fun handleEntityRaycast(context: RaycastEventContext) {
        events.values.forEach {event ->
            if (event.canTrigger() && event.isTargetEntity(context)) {
                event.onEntityRaycasted(context)
            }
        }
    }
}