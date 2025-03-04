package dev.mufaro.whispersOfTheVoid.client.entity

import dev.mufaro.whispersOfTheVoid.client.entity.model.VoidwraithModel
import dev.mufaro.whispersOfTheVoid.client.entity.render.VoidwraithEntityRenderer
import dev.mufaro.whispersOfTheVoid.entity.EntityRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry

object EntityRegistry {
    fun register() {
        EntityRendererRegistry.register(EntityRegistry.Voidwraith) { ctx ->
            VoidwraithEntityRenderer(ctx)
        }
        EntityModelLayerRegistry.registerModelLayer(VoidwraithModel.LAYER, VoidwraithModel::getTexturedModelData)
    }
}