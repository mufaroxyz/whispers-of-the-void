package dev.mufaro.whispersOfTheVoid.client.util

import dev.mufaro.whispersOfTheVoid.WhispersOfTheVoid
import dev.mufaro.whispersOfTheVoid.network.payloads.EntityRaycastPayload
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.client.MinecraftClient
import net.minecraft.entity.Entity
import net.minecraft.entity.projectile.ProjectileUtil
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.util.hit.HitResult
import net.minecraft.util.math.Box
import net.minecraft.util.math.Vec3d
import net.minecraft.world.RaycastContext
import java.util.UUID

object RaycastUtil {
    private const val HIT_COOLDOWN = 20
    private const val MAX_REACH = 60.0
    private const val RAYCAST_INTERVAL = 5
    private var tickDelta = 1.0f
    private var tickCounter = 0
    private val targetedEntities = mutableSetOf<Class<out Entity>>()
    private val entityCooldowns = mutableMapOf<UUID, Int>()

    fun initialize() {
        ClientTickEvents.END_CLIENT_TICK.register { client ->
            if (targetedEntities.isEmpty()) return@register

            entityCooldowns.entries.removeIf { (_, cooldown) ->
                val newCd = cooldown - 1
                newCd <= 0
            }

            entityCooldowns.replaceAll { _, cooldown -> cooldown - 1 }

            tickCounter++
            if(tickCounter >= RAYCAST_INTERVAL) {
                tickCounter = 0

                raycast(client)
            }
        }
    }

    private fun raycast(client: MinecraftClient) {
        val entityHitResult = performRaycast(client)
        if (entityHitResult != null) {
            val entity = entityHitResult.entity

            if(!entityCooldowns.containsKey(entity.uuid)) {
                WhispersOfTheVoid.Logger.info("Raycast hit entity: ${entity.name}")
                notifyServerOfRaycast(entity)
                entityCooldowns[entity.uuid] = HIT_COOLDOWN
            }
        }
    }

    private fun performRaycast(client: MinecraftClient): EntityHitResult? {
        val cameraEntity = client.cameraEntity ?: return null
        val cameraPosition = cameraEntity.getCameraPosVec(tickDelta)
        val rotationVec = cameraEntity.getRotationVec(tickDelta)
        val endPosition = cameraPosition.add(rotationVec.multiply(MAX_REACH))

        val box = Box(cameraPosition.x, cameraPosition.y, cameraPosition.z,
            endPosition.x, endPosition.y, endPosition.z)
            .expand(1.0)

        val entityHitResult = ProjectileUtil.raycast(
            cameraEntity,
            cameraPosition,
            endPosition,
            box,
            { entity ->
                targetedEntities.any { it.isInstance(entity) } && entity.isAlive &&
                        isEntityInLineOfSight(cameraEntity, entity, cameraPosition) &&
                        !entityCooldowns.containsKey(entity.uuid)
            },
            MAX_REACH * MAX_REACH
        )

        return entityHitResult
    }

    private fun isEntityInLineOfSight(observer: Entity, target: Entity, cameraPosition: Vec3d): Boolean {
        val world = observer.world;
        val targetPos = target.pos.add(0.0, target.height / 2.0, 0.0);

        val result = world.raycast(
            RaycastContext(
                cameraPosition,
                targetPos,
                RaycastContext.ShapeType.COLLIDER,
                RaycastContext.FluidHandling.NONE,
                observer
            )
        )

        return result.type == HitResult.Type.MISS ||
                result.pos.squaredDistanceTo(targetPos) < 0.1
    }

    private fun notifyServerOfRaycast(entity: Entity) {
        ClientPlayNetworking.send(EntityRaycastPayload(entity.uuidAsString))
    }

    fun <T : Entity> targetEntity(entityClass: Class<T>) {
        targetedEntities.add(entityClass)
    }

    fun <T : Entity> untargetEntity(entity: Class<T>) {
        targetedEntities.remove(entity)
    }
}