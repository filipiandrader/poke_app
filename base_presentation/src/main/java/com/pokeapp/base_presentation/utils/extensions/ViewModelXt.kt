package com.pokeapp.base_presentation.utils.extensions

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokeapp.base_presentation.core.ViewState
import com.pokeapp.domain.core.UseCase
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

fun AndroidViewModel.getString(resId: Int, vararg formatArgs: Any): String =
    getApplication<Application>().getString(resId, formatArgs)