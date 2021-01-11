package com.pokeapp.base_presentation.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

interface ViewStateListener {

    fun onStateError(error: Throwable)

    fun onStateLoading()

    fun hideLoading()

    private fun <T> ViewState<T>.handle(
        onError: ((Throwable) -> Unit)? = null,
        onLoading: (() -> Unit)? = null,
        onComplete: (() -> Unit)? = null,
        onSuccess: ((T) -> Unit)? = null
    ) {
        stateHandler(
            onSuccess = {
                hideLoading()
                onSuccess?.invoke(it)
                onComplete?.invoke()
            },
            onError = { error ->
                hideLoading()
                onError?.invoke(error) ?: onStateError(error)
                onComplete?.invoke()
            },
            loading = { onLoading?.invoke() ?: onStateLoading() }
        )
    }

    fun <T> LiveData<ViewState<T>>.onPostValue(
        lifecycleOwner: LifecycleOwner,
        onError: ((Throwable) -> Unit)? = null,
        onComplete: (() -> Unit)? = null,
        onLoading: (() -> Unit)? = null,
        onSuccess: ((T) -> Unit)? = null
    ) {
        observe(lifecycleOwner) {
            it.handle(onError, onLoading, onComplete, onSuccess)
        }
    }
}