package dev.mufaro.whispersOfTheVoid.events.base

sealed class _ReturnForClientExecution<out T> {
    data class SuccessPlayerList<out T>(val value: T) : ReturnForClientExecution<T>()
    data class No(val value: Int = 0) : ReturnForClientExecution<Nothing>()
}