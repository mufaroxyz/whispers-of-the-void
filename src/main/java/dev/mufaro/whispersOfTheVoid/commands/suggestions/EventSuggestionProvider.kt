package dev.mufaro.whispersOfTheVoid.commands.suggestions

import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.suggestion.SuggestionProvider
import com.mojang.brigadier.suggestion.Suggestions
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import dev.mufaro.whispersOfTheVoid.events.mod.HorrorEventRegister
import net.minecraft.server.command.ServerCommandSource
import java.util.concurrent.CompletableFuture

class EventSuggestionProvider : SuggestionProvider<ServerCommandSource> {
    override fun getSuggestions(
        ctx: CommandContext<ServerCommandSource>?,
        builder: SuggestionsBuilder?
    ): CompletableFuture<Suggestions> {
        val events = HorrorEventRegister.getEventManager().getEvents()

        events.forEach { (category, eventList) ->
            eventList.forEach { event ->
                builder?.suggest("${category}.${event.identifier}")
            }
        }

        return builder?.buildFuture() ?: Suggestions.empty()
    }
}