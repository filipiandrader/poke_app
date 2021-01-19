package com.pokeapp.presentation.details

import androidx.lifecycle.ViewModel
import com.pokeapp.base_presentation.model.PokemonBinding

/**
 * Created by Filipi Andrade on 31/03/2020
 */
class PokemonDetailsViewModel : ViewModel() {

    /*private var mState = MutableLiveData<ViewState<String>>()
    private var mStateInfo = MutableLiveData<ViewState<PokemonBinding>>()

    init {
        mState.value = ViewState.initializing()
        mStateInfo.value = ViewState(data = null, state = State.LOADING)
    }*/
    
    fun getPokemonInfo(id: Int) {
        /*dataSource.getPokemonInfo(id,
            onSuccess = {
                mStateInfo.postValue(ViewState.success(it))
            },
            onFailure = {
                mStateInfo.postValue(ViewState.failure(it))
            })*/
    }

    fun doFavouritePokemon(pokemon: PokemonBinding) {
        /*dataSource.doFavouritePokemon(pokemon,
                onSuccess = {
                    mState.postValue(ViewState.success())
                },
                onFailure = {
                    mState.postValue(ViewState.failure())
                })*/
    }

    /*fun getState(): LiveData<ViewState<String>> = mState

    fun getStateInfo(): LiveData<ViewState<PokemonBinding>> = mStateInfo*/
}