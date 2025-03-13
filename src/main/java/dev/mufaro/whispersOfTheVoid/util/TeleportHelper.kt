package dev.mufaro.whispersOfTheVoid.util

object TeleportHelper {
    private val SKIP_POST_TELEPORT: ThreadLocal<Boolean> = ThreadLocal.withInitial { false }

    @JvmStatic
    fun skipNextPostTeleportTransition() {
        SKIP_POST_TELEPORT.set(true)
    }

    @JvmStatic
    fun shouldSkipPostTeleportTransition(): Boolean {
        return SKIP_POST_TELEPORT.get()
    }

    @JvmStatic
    fun resetSkipFlag() {
        SKIP_POST_TELEPORT.set(false)
    }
}