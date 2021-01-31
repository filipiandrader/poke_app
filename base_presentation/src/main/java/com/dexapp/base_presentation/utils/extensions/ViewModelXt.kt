package com.dexapp.base_presentation.utils.extensions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dexapp.base_presentation.core.ViewState
import com.dexapp.domain.core.UseCase
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

fun <T> viewState() = lazy {
    MutableLiveData<ViewState<T>>()
}

inline fun <V, reified U> V.useCase() where U : UseCase<*, *>, V : ViewModel, V : KoinComponent =
    inject<U> {
        parametersOf(viewModelScope)
    }