package com.betterafter.template.domain.core

sealed class DataState<out T> {
    data object Initial : DataState<Nothing>()

    data class Loading<T>(val data: T? = null) : DataState<T>()

    data class Success<T>(val data: T) : DataState<T>()

    data class Error<T>(
        val error: Throwable,
        val message: String? = null,
        val data: T? = null,
    ) : DataState<T>()

    val isInitial: Boolean get() = this is Initial
    val isLoading: Boolean get() = this is Loading
    val isSuccess: Boolean get() = this is Success
    val isError: Boolean get() = this is Error

    inline fun <R> whenState(
        initial: () -> R,
        loading: (T?) -> R,
        success: (T) -> R,
        error: (Throwable, String?, T?) -> R,
    ): R = when (this) {
        is Initial -> initial()
        is Loading -> loading(data)
        is Success -> success(data)
        is Error -> error(this.error, message, data)
    }

    companion object {
        suspend fun <T> guard(
            onError: ((Throwable) -> String)? = null,
            call: suspend () -> T,
        ): DataState<T> = try {
            Success(call())
        } catch (e: Throwable) {
            Error(e, message = onError?.invoke(e) ?: e.message)
        }
    }
}
