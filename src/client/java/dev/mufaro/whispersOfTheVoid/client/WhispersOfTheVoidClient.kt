package dev.mufaro.whispersOfTheVoid.client

import dev.mufaro.whispersOfTheVoid.WhispersOfTheVoid.Companion.MOD_ID
import dev.mufaro.whispersOfTheVoid.client.data.ClientData
import dev.mufaro.whispersOfTheVoid.client.events.ClientHorrorEventHandler
import dev.mufaro.whispersOfTheVoid.client.events.ClientHorrorEvents
import dev.mufaro.whispersOfTheVoid.client.model.VoidwraithModel
import dev.mufaro.whispersOfTheVoid.client.network.NetReceiverManager
import dev.mufaro.whispersOfTheVoid.client.render.VoidwraithEntityRenderer
import dev.mufaro.whispersOfTheVoid.entity.EntityRegistry
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.model.EntityModel
import org.slf4j.LoggerFactory

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
