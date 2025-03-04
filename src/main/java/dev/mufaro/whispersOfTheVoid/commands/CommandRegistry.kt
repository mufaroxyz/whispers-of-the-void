package dev.mufaro.whispersOfTheVoid.commands

import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.builder.RequiredArgumentBuilder.argument
import dev.mufaro.whispersOfTheVoid.commands.handlers.ConfigCommandHandler
import dev.mufaro.whispersOfTheVoid.commands.handlers.FearCommandHandler
import dev.mufaro.whispersOfTheVoid.commands.handlers.TriggerCommandHandler
import dev.mufaro.whispersOfTheVoid.commands.suggestions.ConfigSuggestionProvider
import dev.mufaro.whispersOfTheVoid.commands.suggestions.EventSuggestionProvider
import dev.mufaro.whispersOfTheVoid.data.worldConfigTypeMap
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.command.argument.EntityArgumentType
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource

object CommandRegistry {
    fun register() {
        CommandRegistrationCallback.EVENT.register({ dispatcher, registryAccess, env ->
            dispatcher.register(CommandManager.literal("fear")
                .then(CommandManager.literal("get")
                    .then(CommandManager.argument("target", EntityArgumentType.player())
                .executes(FearCommandHandler::getFearLevel)
            ))
                .then(CommandManager.literal("set")
                    .requires({ source -> source.hasPermissionLevel(2) })
                    .then(CommandManager.argument("target", EntityArgumentType.player())
                    .then(CommandManager.argument("fearLevel", IntegerArgumentType.integer())
                    .executes(FearCommandHandler::setFearLevel)
            ))))

            dispatcher.register(CommandManager.literal("whispersofthevoid")
                .then(CommandManager.literal("config")
                    .then(CommandManager.literal("get")
                        .then(CommandManager.argument("configKey", StringArgumentType.string())
                            .suggests(ConfigSuggestionProvider())
                            .executes(ConfigCommandHandler::getConfig)
                        ))

                    .then(CommandManager.literal("set")
                        .requires { source -> source.hasPermissionLevel(2) }
                        .then(argument<ServerCommandSource, String>("configKey", StringArgumentType.string())
                            .suggests(ConfigSuggestionProvider())
                            .then(argument<ServerCommandSource, String>("configValue", StringArgumentType.string())
                                .suggests { context, builder ->
                                    val configKey = context.getArgument("configKey", String::class.java)
                                    when (worldConfigTypeMap[configKey]) {
                                        "boolean", "Boolean" -> {
                                            builder.suggest("true")
                                            builder.suggest("false")
                                        }
                                    }
                                    builder.buildFuture()
                                }
                                .executes(ConfigCommandHandler::setConfig)
                            )
                        )
                    )
                )
                .then(CommandManager.literal("trigger")
                    .then(argument<ServerCommandSource?, String?>("eventName", StringArgumentType.string())
                        .suggests(EventSuggestionProvider())
                        .executes(TriggerCommandHandler::handleTriggerCommand)
                    )
                )
            )
        })
    }
}