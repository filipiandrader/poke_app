package com.pokeapp.presentation.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pokeapp.domain.favourite.FavouriteDataSource
import com.pokeapp.presentation.State
import com.pokeapp.presentation.ViewState
import com.pokeapp.presentation.model.Pokemon
import com.pokeapp.presentation.model.Type

/**
 * Created by Filipi Andrade on 31/03/2020
 */
class FavouriteViewModel(private val dataSource: FavouriteDataSource) : ViewModel() {

    private var mState = MutableLiveData<ViewState<MutableList<Pokemon>>>()
    private var mStateByGeneration = MutableLiveData<ViewState<MutableList<Pokemon>>>()
    private var mStateTypes = MutableLiveData<ViewState<MutableList<Type>>>()
    private var mStateByType = MutableLiveData<ViewState<MutableList<Pokemon>>>()

    init {
        mState.value = ViewState(data = null, state = State.LOADING)
        mStateByGeneration.value = ViewState(data = null, state = State.LOADING)
        mStateTypes.value = ViewState(data = null, state = State.LOADING)
        mStateByType.value = ViewState(data = null, state = State.LOADING)
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
    fun getPokemonByGenenration(region: String) {
        mStateByGeneration.postValue(ViewState.loading())
        dataSource.getPokemonByGeneration(region,
                onSuccess = {
                    mStateByGeneration.postValue(ViewState.success(it))
                },
                onFailure = {
                    mStateByGeneration.postValue(ViewState.failure())
                }
        )
    }

    fun getTypes() {
        dataSource.getAllTypes(
                onSuccess = {
                    mStateTypes.postValue(ViewState.success(it))
                },
                onFailure = {
                    mStateTypes.postValue(ViewState.failure())
                }
        )
    }

    fun getPokemonByType(type: String) {
        mStateByType.postValue(ViewState.loading())
        dataSource.getPokemonByType(type,
                onSuccess = {
                    mStateByType.postValue(ViewState.success(it))
                },
                onFailure = {
                    mStateByType.postValue(ViewState.failure())
                }
        )
    }

    fun getState(): LiveData<ViewState<MutableList<Pokemon>>> = mState

    fun getStateByGeneration(): LiveData<ViewState<MutableList<Pokemon>>> = mStateByGeneration

    fun getStateTypes(): LiveData<ViewState<MutableList<Type>>> = mStateTypes

    fun getStateByType(): LiveData<ViewState<MutableList<Pokemon>>> = mStateByType

    override fun onCleared() {
        super.onCleared()
        dataSource.cancelJob()
    }
}