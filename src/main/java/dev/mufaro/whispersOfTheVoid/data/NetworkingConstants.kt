package dev.mufaro.whispersOfTheVoid.data

import dev.mufaro.whispersOfTheVoid.WhispersOfTheVoid
import net.minecraft.util.Identifier

object NetworkingConstants {
    val INITIAL_SYNC_PACKET_ID: Identifier = Identifier.of(WhispersOfTheVoid.MOD_ID, "initial_sync")
    val HORROR_EVENT_PACKET_ID: Identifier =  Identifier.of(WhispersOfTheVoid.MOD_ID, "horror_event")
    val ENTITY_RAYCAST_PACKET_ID: Identifier = Identifier.of(WhispersOfTheVoid.MOD_ID, "entity_raycast")
}