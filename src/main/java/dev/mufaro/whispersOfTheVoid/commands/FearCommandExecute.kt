package dev.mufaro.whispersOfTheVoid.commands

import com.mojang.brigadier.context.CommandContext
import dev.mufaro.whispersOfTheVoid.data.PlayerFearState
import net.minecraft.entity.LivingEntity
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text

object FearCommandExecute {
    fun getFearLevel(ctx: CommandContext<ServerCommandSource>): Int {
        val source = ctx.source;
        val player = source.player;

        val playerData = PlayerFearState.getPlayerState(player as LivingEntity);
        val fearLevel = playerData.fearLevel;

        source.sendFeedback({ Text.of("Fear level: $fearLevel") }, false);
        return 1;
    }

    fun setFearLevel(ctx: CommandContext<ServerCommandSource>): Int {
        val source = ctx.source;
        val player = source.player;

        val playerData = PlayerFearState.getPlayerState(player as LivingEntity);
        val fearLevel = ctx.getArgument("fearLevel", Int::class.java);

        playerData.fearLevel = fearLevel;
        source.sendFeedback({ Text.of("Set fear level to $fearLevel") }, false);
        return 1;
    }
}