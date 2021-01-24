package com.pokeapp.base_presentation.utils.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.pokeapp.base_presentation.core.ViewState
import com.pokeapp.base_presentation.core.ViewState.Status.*
import com.pokeapp.base_presentation.core.isError
import com.pokeapp.base_presentation.core.isLoading
import com.pokeapp.base_presentation.core.isSuccess
import com.pokeapp.base_presentation.utils.EventLiveData

fun <T> MutableLiveData<ViewState<T>>.postNeutral() {
    value = ViewState(NEUTRAL, null, null)
}

fun <T> MutableLiveData<ViewState<T>>.postSuccess(data: T) {
    postValue(ViewState(SUCCESS, data, null))
}

fun <T> MutableLiveData<ViewState<T>>.postError(error: Throwable?) {
    postValue(ViewState(ERROR, null, error))
}

fun <T> MutableLiveData<ViewState<T>>.postLoading() {
    postValue(ViewState(LOADING, null, null))
}

fun <T> MutableLiveData<T>.asLiveData(): LiveData<T> = this

fun <T> LiveData<ViewState<T>>.isLoading() = value.isLoading()

fun <T> LiveData<ViewState<T>>.isSuccess() = value.isSuccess()

fun <T> LiveData<ViewState<T>>.isError() = value.isError()

fun <T> LiveData<ViewState<T>>.observe(
    lifecycleOwner: LifecycleOwner,
    isSingleEvent: Boolean = false,
    event: (ViewState<T>) -> Unit
) {
    observe(lifecycleOwner, Observer {
        (this as? EventLiveData)?.apply {
            getContent(isSingleEvent)?.let { event(it) }
            return@Observer
        }
        value?.let { event(it) }
    })
}

