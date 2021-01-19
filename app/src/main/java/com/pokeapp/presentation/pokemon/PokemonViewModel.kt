package com.pokeapp.presentation.pokemon

import androidx.lifecycle.ViewModel

/**
 * Created by Filipi Andrade on 29/03/2020
 */

class PokemonViewModel : ViewModel() {

/*    private var mState = MutableLiveData<ViewState<MutableList<PokemonBinding>>>()
    private var mStateByGeneration = MutableLiveData<ViewState<MutableList<PokemonBinding>>>()
    private var mStateTypes = MutableLiveData<ViewState<MutableList<TypeBinding>>>()
    private var mStateByType = MutableLiveData<ViewState<MutableList<PokemonBinding>>>()

    init {
        mState.value = ViewState(data = null, state = State.LOADING)
        mStateByGeneration.value = ViewState(data = null, state = State.LOADING)
        mStateTypes.value = ViewState(data = null, state = State.LOADING)
        mStateByType.value = ViewState(data = null, state = State.LOADING)
    }*/

    fun getAllPokemon(offset: Int) {
        /*if (offset == 0) {
            mState.postValue(ViewState.loading())
        }
        dataSource.getAllPokemons(offset,
                onSuccess = {
                    mState.postValue(ViewState.success(it))
                },
                onFailure = {
                    mState.postValue(ViewState.failure(it))
                }
        )*/
    }

    fun getPokemonByGenenration(id: Int) {
       /* mStateByGeneration.postValue(ViewState.loading())
        dataSource.getPokemonByGeneration(id,
                onSuccess = {
                    mStateByGeneration.postValue(ViewState.success(it))
                },
                onFailure = {
                    mStateByGeneration.postValue(ViewState.failure(it))
                }
        )*/
    }

    fun getTypes() {
        /*dataSource.getAllTypes(
                onSuccess = {
                    mStateTypes.postValue(ViewState.success(it))
                },
                onFailure = {
                    mStateTypes.postValue(ViewState.failure())
                }
        )*/
    }

    fun getPokemonByType(id: Int) {
        /*mStateByType.postValue(ViewState.loading())
        dataSource.getPokemonByType(id,
                onSuccess = {
                    mStateByType.postValue(ViewState.success(it))
                },
                onFailure = {
                    mStateByType.postValue(ViewState.failure(it))
                }
        )*/
    }

/*    fun getState(): LiveData<ViewState<MutableList<PokemonBinding>>> = mState

    fun getStateByGeneration(): LiveData<ViewState<MutableList<PokemonBinding>>> = mStateByGeneration

    fun getStateTypes(): LiveData<ViewState<MutableList<TypeBinding>>> = mStateTypes

    fun getStateByType(): LiveData<ViewState<MutableList<PokemonBinding>>> = mStateByType*/
}