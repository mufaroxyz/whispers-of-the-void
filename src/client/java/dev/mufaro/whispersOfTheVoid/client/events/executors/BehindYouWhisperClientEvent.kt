package dev.mufaro.whispersOfTheVoid.client.events.executors

import dev.mufaro.whispersOfTheVoid.WhispersOfTheVoid
import dev.mufaro.whispersOfTheVoid.client.data.ClientEventContext
import dev.mufaro.whispersOfTheVoid.client.data.ClientHorrorEvent


// this is a test event, todo: remove later
class BehindYouWhisperClientEvent : ClientHorrorEvent {
    override fun execute(ctx: ClientEventContext) {
        WhispersOfTheVoid.Logger.info("executing in client: ${ctx.client.world?.isClient}")
    }
}