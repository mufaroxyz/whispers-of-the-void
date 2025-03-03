package dev.mufaro.whispersOfTheVoid.client.model;

import dev.mufaro.whispersOfTheVoid.WhispersOfTheVoid;
import dev.mufaro.whispersOfTheVoid.client.render.VoidwraithEntityRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class VoidwraithModel extends EntityModel<VoidwraithEntityRenderState> {
    public static final EntityModelLayer LAYER = new EntityModelLayer(Identifier.of(WhispersOfTheVoid.MOD_ID, "voidwrath"), "main");

    private final ModelPart root;
    private final ModelPart head;

    public VoidwraithModel(ModelPart root) {
        super(root);
        this.root = root.getChild("root");
        this.head = this.root.getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData body_r1 = root.addChild("body_r1", ModelPartBuilder.create().uv(0, 16).cuboid(-7.0F, -14.0F, -1.0F, 8.0F, 14.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -12.0F, -3.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData arms = root.addChild("arms", ModelPartBuilder.create().uv(0, 34).cuboid(-3.0F, -12.0F, 11.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(24, 32).cuboid(-3.0F, -12.0F, -1.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, -14.0F, -7.0F));

        ModelPartData legs = root.addChild("legs", ModelPartBuilder.create().uv(32, 0).cuboid(-3.0F, -12.0F, -5.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(24, 16).cuboid(-3.0F, -12.0F, -1.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, 0.0F, 1.0F));

        ModelPartData head = root.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -34.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData dongle = head.addChild("dongle", ModelPartBuilder.create(), ModelTransform.pivot(1.0F, -37.7303F, 4.8658F));

        ModelPartData cube_r1 = dongle.addChild("cube_r1", ModelPartBuilder.create().uv(20, 38).cuboid(-3.0F, -2.0F, -2.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3054F, 0.0F, 0.0F));

        ModelPartData cube_r2 = dongle.addChild("cube_r2", ModelPartBuilder.create().uv(16, 38).cuboid(-3.0F, -2.0F, -2.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.6297F, -0.2045F, -0.1309F, 0.0F, 0.0F));

        ModelPartData cube_r3 = dongle.addChild("cube_r3", ModelPartBuilder.create().uv(16, 34).cuboid(-3.0F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 4.7303F, -3.8658F, -0.4363F, 0.0F, 0.0F));

        ModelPartData dongle2 = head.addChild("dongle2", ModelPartBuilder.create(), ModelTransform.of(-4.0F, -37.7303F, -5.1342F, 0.0F, 3.1416F, 0.0F));

        ModelPartData cube_r4 = dongle2.addChild("cube_r4", ModelPartBuilder.create().uv(40, 19).cuboid(-3.0F, -2.0F, -2.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3054F, 0.0F, 0.0F));

        ModelPartData cube_r5 = dongle2.addChild("cube_r5", ModelPartBuilder.create().uv(40, 16).cuboid(-3.0F, -2.0F, -2.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.6297F, -0.2045F, -0.1309F, 0.0F, 0.0F));

        ModelPartData cube_r6 = dongle2.addChild("cube_r6", ModelPartBuilder.create().uv(20, 34).cuboid(-3.0F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 4.7303F, -3.8658F, -0.4363F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

//    public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
//        this.resetTransforms();
//    }
//
//    @Override
//    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
//        this.root.render(matrices, vertexConsumer, light, overlay, color);
//    }
}