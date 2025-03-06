package dev.mufaro.whispersOfTheVoid.events.mod.raycast

import java.util.*

class RaycastEventManager {
    private val events = mutableMapOf<String, RaycastEvent>()

    fun register(event: RaycastEvent) {
        events[event.getEventId()] = event
    }

    fun handleEntityRaycast(entityUUID: UUID) {
        events.values.forEach {event ->
            if (event.canTrigger() && event.isTargetEntity(entityUUID)) {
                event.onEntityRaycasted(entityUUID)
            }
        }
    }
}