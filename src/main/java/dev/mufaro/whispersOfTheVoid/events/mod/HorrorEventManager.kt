package dev.mufaro.whispersOfTheVoid.events.mod

import jdk.jfr.Event

class HorrorEventManager {
    private val events = mutableMapOf<EventType, MutableList<HorrorEvent>>()

    fun getEvents(): Map<EventType, List<HorrorEvent>> {
        return events
    }

    fun registerEvent(event: HorrorEvent) {
        events.getOrPut(event.type) { mutableListOf() }.add(event)
    }

    fun triggerEvent(context: EventContext, category: EventType, identifier: String) {
        if (category == EventType.NONE) return;

        val event = events[category]?.find { it.identifier == identifier } ?: throw IllegalArgumentException("Event $identifier not found")
        event.execute(context)
    }

    fun triggerRandomEvent(context: EventContext, ofType: EventType?) {
        if (events.isEmpty() || (ofType != null && ofType == EventType.NONE)) return;

        val possibleEvents =
            if (ofType == null)
                events.values
                    .flatten()
                    .filter { it.minFearLevel <= context.fearLevel }
            else events[ofType] ?: return

        val totalWeight = possibleEvents.sumOf { it.weight }
        if (totalWeight <= 0) return;

        var random = context.world.random.nextInt(totalWeight)
        for (event in possibleEvents) {
            random -= event.weight
            if (random < 0) {
                event.execute(context)
                return
            }
        }
    }
}