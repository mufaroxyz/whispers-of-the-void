package dev.mufaro.whispersOfTheVoid.client.render

import dev.mufaro.whispersOfTheVoid.WhispersOfTheVoid
import dev.mufaro.whispersOfTheVoid.client.model.VoidwraithModel
import dev.mufaro.whispersOfTheVoid.entity.voidwraith.VoidwraithEntity
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.MobEntityRenderer
import net.minecraft.util.Identifier

class VoidwraithEntityRenderer(ctx: EntityRendererFactory.Context) :
    MobEntityRenderer<VoidwraithEntity, VoidwraithEntityRenderState, VoidwraithModel>(
        ctx,
        VoidwraithModel(ctx.getPart(VoidwraithModel.LAYER)),
        0.5f
    ) {
    companion object {
        val TEXTURE: Identifier = Identifier.of(WhispersOfTheVoid.MOD_ID, "textures/entity/voidwraith/voidwraith.png")
    }

    override fun createRenderState(): VoidwraithEntityRenderState {
        return VoidwraithEntityRenderState()
    }

    override fun getTexture(state: VoidwraithEntityRenderState?): Identifier {
        return TEXTURE
    }

    override fun updateRenderState(
        livingEntity: VoidwraithEntity?,
        livingEntityRenderState: VoidwraithEntityRenderState?,
        f: Float
    ) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f)
    }
}