package dev.mufaro.whispersOfTheVoid.entity

import dev.mufaro.whispersOfTheVoid.entity.voidwraith.VoidwraithEntityRegister
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry

object EntityRegistry {
    var Voidwraith = Registry.register(Registries.ENTITY_TYPE, VoidwraithEntityRegister.key, VoidwraithEntityRegister.entityType)
}
