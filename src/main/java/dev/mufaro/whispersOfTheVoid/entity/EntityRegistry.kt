package dev.mufaro.whispersOfTheVoid.entity

import dev.mufaro.whispersOfTheVoid.entity.voidwraith.VoidwraithEntity
import dev.mufaro.whispersOfTheVoid.entity.voidwraith.VoidwraithEntityRegister
import net.minecraft.entity.EntityType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry

object EntityRegistry {
    var Voidwraith: EntityType<VoidwraithEntity> = Registry.register(Registries.ENTITY_TYPE, VoidwraithEntityRegister.key, VoidwraithEntityRegister.entityType)
}
