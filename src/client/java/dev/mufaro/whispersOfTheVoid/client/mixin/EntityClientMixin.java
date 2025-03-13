package dev.mufaro.whispersOfTheVoid.client.mixin;

import dev.mufaro.whispersOfTheVoid.util.TeleportHelper;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.TeleportTarget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityClientMixin {
    @Inject(
            method = "setPos",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/EntityChangeListener;updateEntityPosition()V",
                    shift = At.Shift.AFTER
            ),
            cancellable = true)
    private void earlySetPos(double x, double y, double z, CallbackInfo ci) {
        ci.cancel();
    }
}