package dev.mufaro.whispersOfTheVoid.events.mod.raycast.events

import dev.mufaro.whispersOfTheVoid.WhispersOfTheVoid
import dev.mufaro.whispersOfTheVoid.entity.voidwraith.VoidwraithEntity
import dev.mufaro.whispersOfTheVoid.events.mod.raycast.RaycastEvent
import dev.mufaro.whispersOfTheVoid.events.mod.raycast.RaycastEventContext
import dev.mufaro.whispersOfTheVoid.util.ServerTaskScheduler
import dev.mufaro.whispersOfTheVoid.util.TeleportHelper
import net.minecraft.command.argument.EntityAnchorArgumentType
import net.minecraft.registry.RegistryKeys
import net.minecraft.server.MinecraftServer
import net.minecraft.server.ServerTask
import net.minecraft.util.math.Vec3d
import java.util.*
import kotlin.math.cos
import kotlin.math.sin

object VoidwraithRaycastEvent : RaycastEvent<VoidwraithEntity> {
    override val targetedEntity: Class<VoidwraithEntity>
        get() = VoidwraithEntity::class.java

    private fun teleportBehindPlayer(context: RaycastEventContext): Vec3d {
        val player = context.player
        val entity = this.getEntity(context) as VoidwraithEntity

        val yaw = Math.toRadians(player.yaw.toDouble())

        val x = player.x + sin(yaw)
        val z = player.z - cos(yaw)

        TeleportHelper.skipNextPostTeleportTransition()
        entity.updateTrackedPositionAndAngles(x, player.y, z, player.yaw, player.pitch, 1)
        return entity.eyePos
    }

    private fun lookAtPos(context: RaycastEventContext, pos: Vec3d) {
        val player = context.player

        player.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, pos)
    }

    override fun isTargetEntity(context: RaycastEventContext): Boolean {
        val targetEntity = this.getEntity(context)
        return targetEntity != null && targetEntity is VoidwraithEntity
    }

    override fun onEntityRaycasted(context: RaycastEventContext) {
        val entity = this.getEntity(context) as VoidwraithEntity

        WhispersOfTheVoid.Logger.info("Voidwraith raycasted by ${context.player.name.string}")
//        entity.discard()
        ServerTaskScheduler.chain(
            ServerTaskScheduler.ChainOperation(0) {
                val newLookPos = teleportBehindPlayer(context)
//                lookAtPos(context, newLookPos)
            },
            ServerTaskScheduler.ChainOperation(10) {
            }
        )
    }
}