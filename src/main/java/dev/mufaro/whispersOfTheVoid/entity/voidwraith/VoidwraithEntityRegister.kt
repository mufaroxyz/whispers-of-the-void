package dev.mufaro.whispersOfTheVoid.entity.voidwraith

import dev.mufaro.whispersOfTheVoid.WhispersOfTheVoid
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier

object VoidwraithEntityRegister {
    private var id: Identifier = Identifier.of(WhispersOfTheVoid.MOD_ID, "voidwrath")
    var key: RegistryKey<EntityType<*>> = RegistryKey.of(RegistryKeys.ENTITY_TYPE, id)

    var entityType: EntityType<VoidwraithEntity> = EntityType.Builder.create(
        { type: EntityType<VoidwraithEntity>, world -> VoidwraithEntity(type, world) },
        SpawnGroup.MONSTER
    ).dimensions(1f, 2.35f).build(key)
}