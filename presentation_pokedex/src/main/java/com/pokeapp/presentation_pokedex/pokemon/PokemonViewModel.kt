package com.pokeapp.presentation_pokedex.pokemon

import androidx.lifecycle.ViewModel
import com.pokeapp.base_presentation.mapper.region.generation.GenerationMapper
import com.pokeapp.base_presentation.mapper.region.pokedex.PokedexMapper
import com.pokeapp.base_presentation.mapper.region.type.TypeMapper
import com.pokeapp.base_presentation.model.GenerationBinding
import com.pokeapp.base_presentation.model.PokemonBinding
import com.pokeapp.base_presentation.model.TypeBinding
import com.pokeapp.base_presentation.utils.extensions.*
import com.pokeapp.domain.interactor.generation.GetGeneration
import com.pokeapp.domain.interactor.generation.GetPokemonByGeneration
import com.pokeapp.domain.interactor.pokedex.GetPokedex
import com.pokeapp.domain.interactor.type.GetPokemonByType
import com.pokeapp.domain.interactor.type.GetType
import org.koin.core.KoinComponent

/**
 * Created by Filipi Andrade on 29/03/2020
 */

class PokemonViewModel : ViewModel(), KoinComponent {

    private val getPokedex: GetPokedex by useCase()
    private val getType: GetType by useCase()
    private val getPokemonByType: GetPokemonByType by useCase()
    private val getGeneration: GetGeneration by useCase()
    private val getPokemonByGeneration: GetPokemonByGeneration by useCase()

    private val _fetchPokedexViewState by viewState<List<PokemonBinding>>()
    private val _fetchTypeViewState by viewState<List<TypeBinding>>()
    private val _fetchPokedexByTypeTypeViewState by viewState<List<PokemonBinding>>()
    private val _fetchGenerationViewState by viewState<List<GenerationBinding>>()
    private val _fetchPokedexByGenerationTypeViewState by viewState<List<PokemonBinding>>()

    val fetchPokedexViewState = _fetchPokedexViewState.asLiveData()
    val fetchTypeViewState = _fetchTypeViewState.asLiveData()
    val fetchPokedexByTypeTypeViewState = _fetchPokedexByTypeTypeViewState.asLiveData()
    val fetchGenerationViewState = _fetchGenerationViewState.asLiveData()
    val fetchPokedexByGenerationTypeViewState = _fetchPokedexByGenerationTypeViewState.asLiveData()

    fun getAllPokemon(offset: Int, previous: Int) {
        if (previous == 0) _fetchPokedexViewState.postLoading()

        getPokedex(
            params = GetPokedex.Params(offset, previous),
            onSuccess = { _fetchPokedexViewState.postSuccess(PokedexMapper.listFromDomain(it)) },
            onError = { _fetchPokedexViewState.postError(it) }
        )
    }

    fun getGenerations() {
        getGeneration(
            onSuccess = { _fetchGenerationViewState.postSuccess(GenerationMapper.listFromDomain(it)) },
            onError = { _fetchGenerationViewState.postError(it) }
        )
    }

    fun getPokemonByGenenration(id: Int) {
        _fetchPokedexByGenerationTypeViewState.postLoading()

        getPokemonByGeneration(
            params = GetPokemonByGeneration.Params(id),
            onSuccess = {
                _fetchPokedexByGenerationTypeViewState.postSuccess(
                    PokedexMapper.listFromDomain(it)
                )
            },
            onError = { _fetchPokedexByGenerationTypeViewState.postError(it) }
        )
    }

    fun getTypes() {
        getType(
            onSuccess = { _fetchTypeViewState.postSuccess(TypeMapper.listFromDomain(it)) },
            onError = { _fetchTypeViewState.postError(it) }
        )
    }

    fun getPokemonByType(name: String) {
        _fetchPokedexByTypeTypeViewState.postLoading()

        getPokemonByType(
            params = GetPokemonByType.Params(name),
            onSuccess = {
                _fetchPokedexByTypeTypeViewState.postSuccess(
                    PokedexMapper.listFromDomain(
                        it
                    )
                )
            },
            onError = { _fetchPokedexByTypeTypeViewState.postError(it) }
        )
    }
}