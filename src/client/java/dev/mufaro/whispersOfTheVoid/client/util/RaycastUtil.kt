package dev.mufaro.whispersOfTheVoid.client.util

import dev.mufaro.whispersOfTheVoid.WhispersOfTheVoid
import dev.mufaro.whispersOfTheVoid.network.payloads.EntityRaycastPayload
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.client.MinecraftClient
import net.minecraft.entity.Entity
import net.minecraft.entity.projectile.ProjectileUtil
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.util.math.Box

object RaycastUtil {
    private val MAX_REACH = 60.0
    private val RAYCAST_INTERVAL = 3
    private var tickDelta = 1.0f
    private var tickCounter = 0
    val targetedEntities = mutableSetOf<Class<out Entity>>()

    fun initialize() {
        ClientTickEvents.END_CLIENT_TICK.register { client ->
            if (targetedEntities.isEmpty()) return@register
            
            tickCounter++
            if(tickCounter >= RAYCAST_INTERVAL) {
                tickCounter = 0

                raycast(client)
            }
        }
    }

    fun <T : Entity> getLookedAtEntity(clazz: Class<T>): T? {
        @Suppress("UNCHECKED_CAST")
        return targetedEntities.firstOrNull { clazz.isAssignableFrom(it) } as T?
    }

    private fun raycast(client: MinecraftClient) {
        val cameraEntity = client.cameraEntity ?: return
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
                targetedEntities.any { it.isInstance(entity) } && entity.isAlive
            },
            MAX_REACH * MAX_REACH
        )

        if (entityHitResult is EntityHitResult) {
            val entity = entityHitResult.entity
            WhispersOfTheVoid.Logger.info("client raytraced entity: ${entity.name}")
        }
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