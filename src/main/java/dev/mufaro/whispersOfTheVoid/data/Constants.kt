package dev.mufaro.whispersOfTheVoid.data

object Constants {
    enum class FearLevel {
        NONE,
        LOW,
        MEDIUM,
        HIGH,
        PANIC
    }

    const val FEAR_THRESHOLD_LOW = 3
    const val FEAR_THRESHOLD_MEDIUM = 10
    const val FEAR_THRESHOLD_HIGH = 25
    const val FEAR_THRESHOLD_PANIC = 50
    const val FEAR_THRESHOLD_MAX = 100
}