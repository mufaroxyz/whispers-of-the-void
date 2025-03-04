package dev.mufaro.whispersOfTheVoid.client

import dev.mufaro.whispersOfTheVoid.client.data.ClientData
import dev.mufaro.whispersOfTheVoid.client.events.ClientHorrorEvents
import dev.mufaro.whispersOfTheVoid.client.entity.model.VoidwraithModel
import dev.mufaro.whispersOfTheVoid.client.network.NetReceiverManager
import dev.mufaro.whispersOfTheVoid.client.entity.render.VoidwraithEntityRenderer
import dev.mufaro.whispersOfTheVoid.entity.EntityRegistry
import dev.mufaro.whispersOfTheVoid.entity.voidwraith.VoidwraithEntity
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import net.minecraft.client.MinecraftClient

class WhispersOfTheVoidClient : ClientModInitializer {
    companion object {
        val clientData = ClientData()
    }

    override fun onInitializeClient() {
        NetReceiverManager.initialize()
        ClientHorrorEvents.initialize()

        EntityRendererRegistry.register(EntityRegistry.Voidwraith) { ctx ->
            VoidwraithEntityRenderer(ctx)
        }
        EntityModelLayerRegistry.registerModelLayer(VoidwraithModel.LAYER, VoidwraithModel::getTexturedModelData)
    }
}
