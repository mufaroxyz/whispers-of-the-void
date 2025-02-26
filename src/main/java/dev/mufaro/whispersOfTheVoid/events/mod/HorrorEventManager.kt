package dev.mufaro.whispersOfTheVoid.events.mod

class HorrorEventManager {
    private val events = mutableMapOf<EventType, MutableList<HorrorEvent>>()

    fun registerEvent(event: HorrorEvent) {
        events.getOrPut(event.type) { mutableListOf() }.add(event)
    }

    fun triggerRandomEvent(context: EventContext, ofType: EventType?) {
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