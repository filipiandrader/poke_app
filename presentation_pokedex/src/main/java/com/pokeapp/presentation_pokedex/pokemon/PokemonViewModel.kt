package com.pokeapp.presentation_pokedex.pokemon

import androidx.lifecycle.ViewModel
import com.pokeapp.base_presentation.mapper.region.pokedex.PokedexMapper
import com.pokeapp.base_presentation.mapper.region.type.TypeMapper
import com.pokeapp.base_presentation.model.PokemonBinding
import com.pokeapp.base_presentation.model.TypeBinding
import com.pokeapp.base_presentation.utils.extensions.*
import com.pokeapp.domain.interactor.pokedex.GetPokedex
import com.pokeapp.domain.interactor.type.GetType
import org.koin.core.KoinComponent

/**
 * Created by Filipi Andrade on 29/03/2020
 */

class PokemonViewModel : ViewModel(), KoinComponent {

    private val getPokedex: GetPokedex by useCase()
    private val getType: GetType by useCase()

    private val _fetchPokedexViewState by viewState<List<PokemonBinding>>()
    private val _fetchTypeViewState by viewState<List<TypeBinding>>()

    val fetchPokedexViewState = _fetchPokedexViewState.asLiveData()
    val fetchTypeViewState = _fetchTypeViewState.asLiveData()

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

    fun getAllPokemon(offset: Int, previous: Int) {
        if (previous == 0) _fetchPokedexViewState.postLoading()

        getPokedex(
                params = GetPokedex.Params(offset, previous),
                onSuccess = { _fetchPokedexViewState.postSuccess(PokedexMapper.listFromDomain(it)) },
                onError = { _fetchPokedexViewState.postError(it) }
        )
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
        getType(
                onSuccess = { _fetchTypeViewState.postSuccess(TypeMapper.listFromDomain(it)) },
                onError = { _fetchTypeViewState.postError(it) }
        )
    }

    fun getPokemonByType(name: String) {
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