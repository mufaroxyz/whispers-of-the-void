package dev.mufaro.whispersOfTheVoid.events

import dev.mufaro.whispersOfTheVoid.data.PlayerFearState
import dev.mufaro.whispersOfTheVoid.data.WorldConfigState
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents

object ServerTickHandler {
    private var tickCount = 0

    fun registerServerTickEvents() {
        ServerTickEvents.END_SERVER_TICK.register { server ->
            if (
                tickCount == 24000
                && server.playerManager.playerList.size > 0
                && WorldConfigState.getServerState(server).worldConfig.countTicks
            ) {
                tickCount = 0
                val fearData = PlayerFearState.getServerState(server)
                fearData.players.forEach { (_, playerData) ->
                    playerData.fearLevel += 1
                }
            }

            tickCount++
        }
    }
}