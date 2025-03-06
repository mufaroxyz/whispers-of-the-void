package dev.mufaro.whispersOfTheVoid.events.mod.raycast

import dev.mufaro.whispersOfTheVoid.events.base.Event
import java.util.UUID

abstract class RaycastEvent : Event {
    abstract fun isTargetEntity(entityUUID: UUID): Boolean
    abstract fun onEntityRaycasted(entityUUID: UUID)
    abstract fun getEventId(): String
}