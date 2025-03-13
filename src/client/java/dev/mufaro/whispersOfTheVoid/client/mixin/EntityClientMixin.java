package dev.mufaro.whispersOfTheVoid.client.mixin;

import dev.mufaro.whispersOfTheVoid.util.TeleportHelper;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.TeleportTarget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityClientMixin {
    @Shadow
    protected abstract void setRotation(float yaw, float pitch);

    @Inject(
            method = "lerpPosAndRotation",
            at = @At("HEAD"),
            cancellable = true
    )
    private void skipLerpWhenNeeded(int step, double x, double y, double z, double yaw, double pitch, CallbackInfo ci) {
        Entity entity = (Entity) (Object) this;
        entity.setPosition(x, y, z);
        this.setRotation((float) yaw, (float) pitch);
        ci.cancel();
    }
}