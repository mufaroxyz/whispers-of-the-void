package dev.mufaro.whispersOfTheVoid.entity.voidwraith

import dev.mufaro.whispersOfTheVoid.WhispersOfTheVoid
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier

object VoidwraithEntityRegister {
    var id = Identifier.of(WhispersOfTheVoid.MOD_ID, "voidwrath")
    var key = RegistryKey.of(RegistryKeys.ENTITY_TYPE, id)

    var entityType = EntityType.Builder.create(
        { type: EntityType<VoidwraithEntity>, world -> VoidwraithEntity(type, world) },
        SpawnGroup.MONSTER
    ).build(key)
}