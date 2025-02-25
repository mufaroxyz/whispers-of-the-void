package dev.mufaro.whispersOfTheVoid.commands

import com.mojang.brigadier.arguments.IntegerArgumentType
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.command.argument.EntityArgumentType
import net.minecraft.server.command.CommandManager

object CommandRegister {
    fun initialize() {
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
        })
    }
}