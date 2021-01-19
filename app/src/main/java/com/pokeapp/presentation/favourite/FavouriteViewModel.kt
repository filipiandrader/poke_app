package com.pokeapp.presentation.favourite

import androidx.lifecycle.ViewModel

/**
 * Created by Filipi Andrade on 31/03/2020
 */
class FavouriteViewModel : ViewModel() {

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

    fun getFavouritePokemon() {
       /* mState.postValue(ViewState.loading())
        dataSource.getFavouritePokemon(
                onSuccess = {
                    mState.postValue(ViewState.success(it))
                },
                onFailure = {
                    mState.postValue(ViewState.failure())
                }
        )*/
    }
    fun getPokemonByGenenration(region: String) {
        /*mStateByGeneration.postValue(ViewState.loading())
        dataSource.getPokemonByGeneration(region,
                onSuccess = {
                    mStateByGeneration.postValue(ViewState.success(it))
                },
                onFailure = {
                    mStateByGeneration.postValue(ViewState.failure())
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

    fun getPokemonByType(type: String) {
        /*mStateByType.postValue(ViewState.loading())
        dataSource.getPokemonByType(type,
                onSuccess = {
                    mStateByType.postValue(ViewState.success(it))
                },
                onFailure = {
                    mStateByType.postValue(ViewState.failure())
                }
        )*/
    }

/*    fun getState(): LiveData<ViewState<MutableList<PokemonBinding>>> = mState

    fun getStateByGeneration(): LiveData<ViewState<MutableList<PokemonBinding>>> = mStateByGeneration

    fun getStateTypes(): LiveData<ViewState<MutableList<TypeBinding>>> = mStateTypes

    fun getStateByType(): LiveData<ViewState<MutableList<PokemonBinding>>> = mStateByType*/
}