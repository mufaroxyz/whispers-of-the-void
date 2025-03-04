package dev.mufaro.whispersOfTheVoid.entity.voidwraith

import net.minecraft.command.argument.EntityAnchorArgumentType
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.MobEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

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

    override fun isCollidable(): Boolean {
        return false
    }

    override fun isAttackable(): Boolean {
        return false
    }

    private fun getClosestPlayer(): PlayerEntity? {
        val player = world.getClosestPlayer(this, 80.0)
        return player
    }

    override fun tick() {
        super.tick()

        val player = getClosestPlayer()
        val playerEyePos: Vec3d = player?.eyePos ?: Vec3d.ZERO

        if (player != null) {
            this.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, playerEyePos)
        }

    }
}