package com.pokeapp.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pokeapp.domain.details.PokemonDetailsDataSource
import com.pokeapp.presentation.State
import com.pokeapp.presentation.ViewState
import com.pokeapp.presentation.model.Pokemon

/**
 * Created by Filipi Andrade on 31/03/2020
 */
class PokemonDetailsViewModel(private val dataSource: PokemonDetailsDataSource) : ViewModel() {

    private var mState = MutableLiveData<ViewState<String>>()
    private var mStateInfo = MutableLiveData<ViewState<Pokemon>>()

    init {
        mState.value = ViewState.initializing()
        mStateInfo.value = ViewState(data = null, state = State.LOADING)
    }
    
    fun getPokemonInfo(id: Int) {
        dataSource.getPokemonInfo(id,
            onSuccess = {
                mStateInfo.postValue(ViewState.success(it))
            },
            onFailure = {
                mStateInfo.postValue(ViewState.failure(it))
            })
    }

    fun doFavouritePokemon(pokemon: Pokemon) {
        dataSource.doFavouritePokemon(pokemon,
                onSuccess = {
                    mState.postValue(ViewState.success())
                },
                onFailure = {
                    mState.postValue(ViewState.failure())
                })
    }

    fun getState(): LiveData<ViewState<String>> = mState

    fun getStateInfo(): LiveData<ViewState<Pokemon>> = mStateInfo

    override fun onCleared() {
        super.onCleared()
        dataSource.cancelJob()
    }
}