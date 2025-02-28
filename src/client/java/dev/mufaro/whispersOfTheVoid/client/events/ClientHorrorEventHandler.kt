package dev.mufaro.whispersOfTheVoid.client.events

import dev.mufaro.whispersOfTheVoid.client.data.ClientEventContext

object ClientHorrorEventHandler {
    private val clientExecutors = mutableMapOf<String, (ClientEventContext) -> Unit>()

    fun registerClientExecutor(identifier: String, executor: (ClientEventContext) -> Unit) {
        clientExecutors[identifier] = executor
    }

    fun executeClientEvent(context: ClientEventContext, identifier: String) {
        clientExecutors[identifier]?.invoke(context)
    }
}