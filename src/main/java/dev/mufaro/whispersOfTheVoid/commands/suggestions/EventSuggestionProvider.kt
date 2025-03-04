package dev.mufaro.whispersOfTheVoid.commands.suggestions

import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.suggestion.SuggestionProvider
import com.mojang.brigadier.suggestion.Suggestions
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import dev.mufaro.whispersOfTheVoid.events.mod.HorrorEventRegistry
import net.minecraft.server.command.ServerCommandSource
import java.util.concurrent.CompletableFuture

class EventSuggestionProvider : SuggestionProvider<ServerCommandSource> {
    override fun getSuggestions(
        ctx: CommandContext<ServerCommandSource>?,
        builder: SuggestionsBuilder?
    ): CompletableFuture<Suggestions> {
        val events = HorrorEventRegistry.getEventManager().getEvents()

        events.forEach { (_, eventList) ->
            eventList.forEach { event ->
                builder?.suggest(event.eventId)
            }
        }

        return builder?.buildFuture() ?: Suggestions.empty()
    }
}