package com.pokeapp.presentation.pokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pokeapp.domain.pokemon.PokemonDataSource
import com.pokeapp.presentation.State
import com.pokeapp.presentation.ViewState
import com.pokeapp.presentation.model.Pokemon

/**
 * Created by Filipi Andrade on 29/03/2020
 */

class PokemonViewModel(private val dataSource: PokemonDataSource) : ViewModel() {

    private var mState = MutableLiveData<ViewState<MutableList<Pokemon>>>()

    init {
        mState.value = ViewState(data = null, state = State.LOADING)
    }

    fun getAllPokemon(offset: Int) {
        if (offset == 0) {
            mState.postValue(ViewState.loading())
        }
        dataSource.getAllPokemons(offset,
                onSuccess = {
                    mState.postValue(ViewState.success(it))
                },
                onFailure = {
                    mState.postValue(ViewState.failure(it))
                }
        )
    }

    fun getState(): LiveData<ViewState<MutableList<Pokemon>>> = mState

    override fun onCleared() {
        super.onCleared()
        dataSource.cancelJob()
    }
}