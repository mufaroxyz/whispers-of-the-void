package dev.mufaro.whispersOfTheVoid.commands

import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.arguments.IntegerArgumentType
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager

object CommandRegister {
    fun initialize() {
        CommandRegistrationCallback.EVENT.register({ dispatcher, registryAccess, env ->
            dispatcher.register(CommandManager.literal("fear")
                .then(CommandManager.literal("get")
                .executes(FearCommandExecute::getFearLevel)
            ).then(CommandManager.literal("set")
                .then(CommandManager.argument("fearLevel", IntegerArgumentType.integer())
                .executes(FearCommandExecute::setFearLevel)
            )))
        })
    }
}