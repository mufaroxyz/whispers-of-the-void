package dev.mufaro.whispersOfTheVoid.commands.handlers

import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.context.CommandContext
import dev.mufaro.whispersOfTheVoid.data.WorldConfigState
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text

object ConfigCommandHandler {
    fun getConfig(ctx: CommandContext<ServerCommandSource>): Int {
        val server = ctx.source.server;
        val configKey = StringArgumentType.getString(ctx, "configKey");

        val serverState = WorldConfigState.getServerState(server);
        val configValue = serverState.worldConfig.getConfigValue(configKey);

        ctx.source.sendFeedback({ Text.of("Value of $configKey: $configValue") }, false);

        return 1;
    }

    fun setConfig(ctx: CommandContext<ServerCommandSource>): Int {
        val server = ctx.source.server;
        val configKey = StringArgumentType.getString(ctx, "configKey");
        val configValue = StringArgumentType.getString(ctx, "configValue");

        val config = WorldConfigState.getServerState(server).worldConfig;
        config.setConfigValue(configKey, configValue);

        ctx.source.sendFeedback({ Text.of("Set $configKey to $configValue") }, false);

        return 1;
    }
}