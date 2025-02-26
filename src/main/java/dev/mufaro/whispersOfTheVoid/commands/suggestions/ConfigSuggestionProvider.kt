package dev.mufaro.whispersOfTheVoid.commands.suggestions

import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.suggestion.SuggestionProvider
import com.mojang.brigadier.suggestion.Suggestions
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import dev.mufaro.whispersOfTheVoid.data.worldConfigTypeMap
import net.minecraft.server.command.ServerCommandSource
import java.util.concurrent.CompletableFuture

class ConfigSuggestionProvider : SuggestionProvider<ServerCommandSource> {
    override fun getSuggestions(
        ctx: CommandContext<ServerCommandSource>,
        builder: SuggestionsBuilder?
    ): CompletableFuture<Suggestions> {
        val configKeys = worldConfigTypeMap.keys

        for (configKey in configKeys) {
            builder?.suggest(configKey)
        }

        return builder?.buildFuture() ?: Suggestions.empty()
    }
}