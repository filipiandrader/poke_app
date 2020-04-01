package com.pokeapp.presentation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pokeapp.domain.favorite.FavoriteDataSource
import com.pokeapp.presentation.State
import com.pokeapp.presentation.ViewState
import com.pokeapp.presentation.model.Pokemon

/**
 * Created by Filipi Andrade on 31/03/2020
 */
class FavoriteViewModel(private val dataSource: FavoriteDataSource) : ViewModel() {

    private var mState = MutableLiveData<ViewState<MutableList<Pokemon>>>()

    init {
        mState.value = ViewState(data = null, state = State.WAITING_DATA)
    }

    fun getFavoritePokemon() {
        mState.postValue(ViewState.loading())
        dataSource.getFavoritePokemon(
                onSuccess = {
                    mState.postValue(ViewState.success(it))
                },
                onFailure = {
                    mState.postValue(ViewState.failure())
                }
        )
    }

    fun getState(): LiveData<ViewState<MutableList<Pokemon>>> = mState

    override fun onCleared() {
        dataSource.cancelJob()
        super.onCleared()
    }
}