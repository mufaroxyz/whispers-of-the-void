package dev.mufaro.whispersOfTheVoid.entity.voidwraith

import dev.mufaro.whispersOfTheVoid.WhispersOfTheVoid
import net.minecraft.command.argument.EntityAnchorArgumentType
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.MobEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import kotlin.math.atan2
import kotlin.math.sqrt

class VoidwraithEntity(entityType: EntityType<out VoidwraithEntity>, world: World) : MobEntity(entityType, world) {
    companion object {
        fun createAttributes(): DefaultAttributeContainer.Builder {
            return createMobAttributes()
                .add(EntityAttributes.GRAVITY, 0.0)
        }
    }

    override fun isAiDisabled(): Boolean {
        return true
    }

    fun getCloserPlayer(): PlayerEntity? {
        val player = world.getClosestPlayer(this, 80.0)
        return player
    }

    override fun tick() {
        super.tick()

        val player = getCloserPlayer()
        val playerEyePos: Vec3d = player?.eyePos ?: Vec3d.ZERO

        if (player != null) {
//            val deltaX = player.x - this.x
//            val deltaZ = player.z - this.z
//            val newYaw = (atan2(deltaZ, deltaX) * 180.0 / Math.PI).toFloat() - 90.0f
//
//            val deltaY = (player.eyeY - this.eyeY)
//            val horizontalDistance = sqrt(deltaX * deltaX + deltaZ * deltaZ)
//            val newPitch = -(atan2(deltaY, horizontalDistance) * 180.0 / Math.PI).toFloat()
//
//            this.bodyYaw = newYaw
//            this.headYaw = newYaw
//            this.pitch = newPitch
//
//            this.setYaw(newYaw)
            this.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, playerEyePos)
        }

    }
}