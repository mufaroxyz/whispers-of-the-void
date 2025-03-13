package dev.mufaro.whispersOfTheVoid.mixin;

import dev.mufaro.whispersOfTheVoid.WhispersOfTheVoid;
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
public class EntityMixin {
    @Inject(
            method = "teleportSameDimension",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/TeleportTarget$PostDimensionTransition;onTransition(Lnet/minecraft/entity/Entity;)V"
            )
    )
    private void cancelTransition(ServerWorld world, TeleportTarget teleportTarget, CallbackInfoReturnable<Entity> cir) {
        System.out.print(TeleportHelper.shouldSkipPostTeleportTransition());

        if (TeleportHelper.shouldSkipPostTeleportTransition()) {
            cir.cancel();
            TeleportHelper.resetSkipFlag();
        }
    }

    @Inject(
            method = "setPos",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/entity/Entity;pos:Lnet/minecraft/util/math/Vec3d;",
                    ordinal = 1,
                    shift = At.Shift.AFTER
            ),
            cancellable = true)
    private void earlySetPos(double x, double y, double z, CallbackInfo ci) {
            ci.cancel();
    }
}