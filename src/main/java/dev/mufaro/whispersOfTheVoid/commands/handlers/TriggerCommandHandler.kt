package dev.mufaro.whispersOfTheVoid.commands.handlers

import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.context.CommandContext
import dev.mufaro.whispersOfTheVoid.WhispersOfTheVoid
import dev.mufaro.whispersOfTheVoid.data.PlayerFearState
import dev.mufaro.whispersOfTheVoid.events.mod.HorrorEventRegistry
import dev.mufaro.whispersOfTheVoid.events.mod.ServerEventContext
import dev.mufaro.whispersOfTheVoid.utils.castStringToEventType
import dev.mufaro.whispersOfTheVoid.utils.numericFearToLevel
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text

object TriggerCommandHandler {
    fun handleTriggerCommand(ctx: CommandContext<ServerCommandSource>): Int {
        val source = ctx.source
        WhispersOfTheVoid.Logger.info("triggering command")
        val player = source.entity
        if (player !is LivingEntity) {
            source.sendError(Text.of("Command can only be executed by a living entity"))
            return 0
        }

        val eventId = StringArgumentType.getString(ctx, "eventName")
        val (category, name) = eventId.split(".")

        val playerData = PlayerFearState.getPlayerState(player as PlayerEntity)

        val eventContext = ServerEventContext(
            pos = player.blockPos,
            world = player.world,
            player = player,
            fearLevel = numericFearToLevel(playerData.fearLevel),
            server = source.server
        )

        return try {
            HorrorEventRegistry.getEventManager().triggerEvent(eventContext, castStringToEventType(category), name)
            source.sendFeedback({ Text.of("Triggered event $eventId") }, true)
            1
        } catch (e: IllegalArgumentException) {
            source.sendError(Text.of(e.message))
            -1
        }
    }
}