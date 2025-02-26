package dev.mufaro.whispersOfTheVoid.data

import dev.mufaro.whispersOfTheVoid.WhispersOfTheVoid
import net.minecraft.util.Identifier

object NetworkingConstants {
    val INITIAL_SYNC_PACKET_ID = Identifier.of(WhispersOfTheVoid.MOD_ID, "initial_sync")
}