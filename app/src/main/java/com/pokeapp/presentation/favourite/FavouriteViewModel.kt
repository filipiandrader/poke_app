package com.pokeapp.presentation.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pokeapp.domain.favourite.FavouriteDataSource
import com.pokeapp.presentation.State
import com.pokeapp.presentation.ViewState
import com.pokeapp.presentation.model.Pokemon

/**
 * Created by Filipi Andrade on 31/03/2020
 */
class FavouriteViewModel(private val dataSource: FavouriteDataSource) : ViewModel() {

    private var mState = MutableLiveData<ViewState<MutableList<Pokemon>>>()

    init {
        mState.value = ViewState(data = null, state = State.LOADING)
    }

    fun getFavouritePokemon() {
        mState.postValue(ViewState.loading())
        dataSource.getFavouritePokemon(
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
        super.onCleared()
        dataSource.cancelJob()
    }
}