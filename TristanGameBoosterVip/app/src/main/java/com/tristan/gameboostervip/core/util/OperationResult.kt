package com.tristan.gameboostervip.core.util

/**
 * Outcome wrapper for any operation that can partially or fully fail because
 * a device/API doesn't support something (Master Prompt §21, §26).
 *
 * This is distinct from Kotlin's [kotlin.Result] because we need a third
 * state — [Unsupported] — that is NOT an error. "This phone can't report
 * CPU temperature" is an expected, common outcome, not a bug to log loudly.
 */
sealed interface OperationResult<out T> {
    data class Success<T>(val value: T) : OperationResult<T>
    data class Failure(val message: String, val cause: Throwable? = null) : OperationResult<Nothing>
    data class Unsupported(val reason: String) : OperationResult<Nothing>
}

inline fun <T, R> OperationResult<T>.map(transform: (T) -> R): OperationResult<R> = when (this) {
    is OperationResult.Success -> OperationResult.Success(transform(value))
    is OperationResult.Failure -> this
    is OperationResult.Unsupported -> this
}

inline fun <T> OperationResult<T>.onSuccess(action: (T) -> Unit): OperationResult<T> {
    if (this is OperationResult.Success) action(value)
    return this
}

fun <T> OperationResult<T>.getOrNull(): T? = (this as? OperationResult.Success)?.value
