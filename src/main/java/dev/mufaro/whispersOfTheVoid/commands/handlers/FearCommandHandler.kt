package dev.mufaro.whispersOfTheVoid.commands.handlers

import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.context.CommandContext
import dev.mufaro.whispersOfTheVoid.data.PlayerFearState
import net.minecraft.command.argument.EntityArgumentType
import net.minecraft.entity.LivingEntity
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text

object FearCommandHandler {
    fun getFearLevel(ctx: CommandContext<ServerCommandSource>): Int {
        val source = ctx.source
        val player = EntityArgumentType.getPlayer(ctx, "target")

        val playerData = PlayerFearState.getPlayerState(player as LivingEntity)
        val fearLevel = playerData.fearLevel

        source.sendFeedback({ Text.of("${player.name.string}' fear level: $fearLevel") }, false)
        return 1
    }

    fun setFearLevel(ctx: CommandContext<ServerCommandSource>): Int {
        val source = ctx.source
        val player = EntityArgumentType.getPlayer(ctx, "target")

        val playerData = PlayerFearState.getPlayerState(player as LivingEntity)
        val fearLevel = IntegerArgumentType.getInteger(ctx, "fearLevel")

        playerData.fearLevel = fearLevel
        source.sendFeedback({ Text.of("Set ${player.name.string}'s fear level to $fearLevel") }, false)
        return 1
    }
}