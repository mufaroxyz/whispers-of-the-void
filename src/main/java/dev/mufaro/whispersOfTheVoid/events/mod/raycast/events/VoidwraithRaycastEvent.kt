package dev.mufaro.whispersOfTheVoid.events.mod.raycast.events

import dev.mufaro.whispersOfTheVoid.entity.voidwraith.VoidwraithEntity
import dev.mufaro.whispersOfTheVoid.events.mod.raycast.RaycastEvent
import dev.mufaro.whispersOfTheVoid.events.mod.raycast.RaycastEventContext
import net.minecraft.server.MinecraftServer
import java.util.*

object VoidwraithRaycastEvent : RaycastEvent<VoidwraithEntity> {
    override val targetedEntity: Class<VoidwraithEntity>
        get() = VoidwraithEntity::class.java

    // todo: complete the implementation of entity searching and executing the event.
    override fun isTargetEntity(context: RaycastEventContext): Boolean {
        val world = context.player.world

        return false;
    }

    override fun onEntityRaycasted(context: RaycastEventContext) {
        TODO("Not yet implemented")
    }
}