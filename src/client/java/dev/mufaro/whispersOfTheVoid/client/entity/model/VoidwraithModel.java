package dev.mufaro.whispersOfTheVoid.client.entity.model;

import dev.mufaro.whispersOfTheVoid.WhispersOfTheVoid;
import dev.mufaro.whispersOfTheVoid.client.entity.render.VoidwraithEntityRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class VoidwraithModel extends EntityModel<VoidwraithEntityRenderState> {
    public static final EntityModelLayer LAYER = new EntityModelLayer(Identifier.of(WhispersOfTheVoid.MOD_ID, "voidwrath"), "main");

    private final ModelPart head;

    public VoidwraithModel(ModelPart root) {
        super(root);
        ModelPart root1 = root.getChild("root");
        ModelPart voidwraith = root1.getChild("voidwraith");
//        ModelPart body = voidwraith.getChild("body");
        this.head = voidwraith.getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData voidwraith = root.addChild("voidwraith", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData body = voidwraith.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(2.0F, -14.0F, -7.0F));

        ModelPartData upper = body.addChild("upper", ModelPartBuilder.create().uv(0, 34).cuboid(-2.0F, -14.0F, 4.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(24, 32).cuboid(-2.0F, -14.0F, -8.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 2.0F, 7.0F));

        upper.addChild("chest_r1", ModelPartBuilder.create().uv(0, 16).cuboid(-7.0F, -14.0F, -1.0F, 8.0F, 14.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.0F, -3.0F, 0.0F, 1.5708F, 0.0F));
        body.addChild("lower", ModelPartBuilder.create().uv(32, 0).cuboid(0.0F, -10.0F, -4.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(24, 16).cuboid(0.0F, -10.0F, 0.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 12.0F, 7.0F));

        ModelPartData head = voidwraith.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -26.0F, 0.0F));

        ModelPartData dongle = head.addChild("dongle", ModelPartBuilder.create(), ModelTransform.pivot(1.0F, -11.7302F, 4.8658F));

        dongle.addChild("cube_r1", ModelPartBuilder.create().uv(20, 38).cuboid(-3.0F, -2.0F, -2.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0, 1.0F, 0.0F));
        dongle.addChild("cube_r2", ModelPartBuilder.create().uv(16, 38).cuboid(-3.0F, -2.0F, -2.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.6297F, -0.2045F, -0, 0.0F, 0.0F));
        dongle.addChild("cube_r3", ModelPartBuilder.create().uv(16, 34).cuboid(-3.0F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 4.7302F, -3.8658F, -0.4363F, 0.0F, 0.0F));

        ModelPartData dongle2 = head.addChild("dongle2", ModelPartBuilder.create(), ModelTransform.of(-4.0F, -11.7302F, -5.1342F, 0.0F, 3.1416F, 0.0F));

        dongle2.addChild("cube_r4", ModelPartBuilder.create().uv(40, 19).cuboid(-3.0F, -2.0F, -2.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0, 0.0F, 0.0F));
        dongle2.addChild("cube_r5", ModelPartBuilder.create().uv(40, 16).cuboid(-3.0F, -2.0F, -2.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.6297F, -0.2045F, -0, 0.0F, 0.0F));
        dongle2.addChild("cube_r6", ModelPartBuilder.create().uv(20, 34).cuboid(-3.0F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 4.7302F, -3.8658F, -0.4363F, 0.0F, 0.0F));

        return TexturedModelData.of(modelData, 64, 64);
    }

    public void setAngles(VoidwraithEntityRenderState state) {
        super.setAngles(state);
        this.setHeadAngles(state.yawDegrees, state.pitch);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0f, 30.0f);
        headPitch = MathHelper.clamp(headPitch, -25.0f, 45.0f);

        this.head.yaw = headYaw * ((float)Math.PI / 180F);
        this.head.roll = -(headPitch * ((float)Math.PI / 180F));
    }
}