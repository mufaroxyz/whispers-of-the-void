package dev.mufaro.whispersOfTheVoid.events.base

import dev.mufaro.whispersOfTheVoid.WhispersOfTheVoid
import dev.mufaro.whispersOfTheVoid.events.internal.ServerPlayConnectionHandler
import dev.mufaro.whispersOfTheVoid.events.internal.ServerPlayNetworkingHandler
import dev.mufaro.whispersOfTheVoid.events.internal.ServerTickHandler
import dev.mufaro.whispersOfTheVoid.events.mod.random.RandomEventManager
import dev.mufaro.whispersOfTheVoid.events.mod.random.events.BehindYouWhisperEvent
import dev.mufaro.whispersOfTheVoid.events.mod.raycast.RaycastEventManager
import dev.mufaro.whispersOfTheVoid.events.mod.raycast.events.VoidwraithRaycastEvent

object EventRegistry {
    val horrorEventManager = RandomEventManager()
    val raycastEventManager = RaycastEventManager()

    fun registerInternal() {
        WhispersOfTheVoid.Logger.info("Registering internal events")

        ServerPlayConnectionHandler.registerServerPlayConnectionEvents()
        ServerTickHandler.registerServerTickEvents()
        ServerPlayNetworkingHandler.register()
    }

    fun registerHorror() {
        WhispersOfTheVoid.Logger.info("Registering Horror Events")

        horrorEventManager.registerEvent(BehindYouWhisperEvent)

        raycastEventManager.register(VoidwraithRaycastEvent)
    }

}