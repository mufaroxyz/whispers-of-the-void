package dev.mufaro.whispersOfTheVoid.data

class WorldConfig {
    var countTicks = true

    fun getConfigValue(key: String): Any? {
        return when (key) {
            "progressFear" -> countTicks
            else -> null
        }
    }

    fun setConfigValue(key: String, value: String) {
        when (key) {
            "progressFear" -> countTicks = value.toBoolean()
        }
    }
}

var worldConfigTypeMap = mapOf(
    "progressFear" to "Boolean"
)