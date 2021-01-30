package com.pokeapp.base_presentation.core

class ViewState<T>(
    private val status: Status = Status.NEUTRAL,
    val data: T? = null,
    private val error: Throwable? = null
) {

    fun stateHandler(onLoading: () -> Unit, onSuccess: (T) -> Unit, onError: (Throwable) -> Unit) {
        when (status) {
            Status.LOADING -> onLoading()
            Status.SUCCESS -> data?.let { onSuccess(it) } ?: throw RuntimeException()
            Status.ERROR -> error?.let { onError(it) } ?: throw RuntimeException()
            else -> {
            }
        }
    }

    enum class Status {
        SUCCESS, ERROR, LOADING, NEUTRAL
    }
}

