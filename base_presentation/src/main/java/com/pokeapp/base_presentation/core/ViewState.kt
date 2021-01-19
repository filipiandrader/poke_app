package com.pokeapp.base_presentation.core

class ViewState<T>(
    val status: Status = Status.NEUTRAL,
    val data: T? = null,
    val error: Throwable? = null
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

fun <T> ViewState<T>?.isLoading() = this?.status?.equals(ViewState.Status.LOADING) ?: false
fun <T> ViewState<T>?.isSuccess() = this?.status?.equals(ViewState.Status.SUCCESS) ?: false
fun <T> ViewState<T>?.isError() = this?.status?.equals(ViewState.Status.ERROR) ?: false

