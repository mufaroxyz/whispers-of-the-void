package dev.mufaro.whispersOfTheVoid.events.mod.raycast.events

import dev.mufaro.whispersOfTheVoid.WhispersOfTheVoid
import dev.mufaro.whispersOfTheVoid.entity.voidwraith.VoidwraithEntity
import dev.mufaro.whispersOfTheVoid.events.mod.raycast.RaycastEvent
import dev.mufaro.whispersOfTheVoid.events.mod.raycast.RaycastEventContext
import net.minecraft.registry.RegistryKeys
import net.minecraft.server.MinecraftServer
import java.util.*

object VoidwraithRaycastEvent : RaycastEvent<VoidwraithEntity> {
    override val targetedEntity: Class<VoidwraithEntity>
        get() = VoidwraithEntity::class.java

    override fun isTargetEntity(context: RaycastEventContext): Boolean {
        val world = context.player.world

        val targetEntity = world.getEntityById(context.entityNetId)

        return targetEntity != null && targetEntity is VoidwraithEntity
    }

    override fun onEntityRaycasted(context: RaycastEventContext) {
        val entity = context.player.world.getEntityById(context.entityNetId) as VoidwraithEntity
        val world = context.server.getWorld(context.player.world.registryKey)

        WhispersOfTheVoid.Logger.info("Voidwraith raycasted by ${context.player.name.string}")
        entity.discard()
    }
}