package dev.mufaro.whispersOfTheVoid.entity.voidwraith

import dev.mufaro.whispersOfTheVoid.WhispersOfTheVoid
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.entity.mob.MobEntity
import net.minecraft.world.World

class VoidwraithEntity(entityType: EntityType<out VoidwraithEntity>, world: World) : HostileEntity(entityType, world) {
    companion object {
        fun createAttributes(): DefaultAttributeContainer.Builder {
            return MobEntity.createMobAttributes().add(
                EntityAttributes.GRAVITY, 0.0
            )
        }
    }

    override fun canMoveVoluntarily(): Boolean {
        return false
    }

    override fun tick() {
        super.tick()

        if (this.world.isClient) {
            WhispersOfTheVoid.Logger.info("Voidwrath entity ticked on client")
        }
    }
}