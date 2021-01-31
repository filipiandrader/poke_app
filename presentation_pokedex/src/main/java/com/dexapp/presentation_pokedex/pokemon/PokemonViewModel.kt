package com.dexapp.presentation_pokedex.pokemon

import androidx.lifecycle.ViewModel
import com.dexapp.base_presentation.mapper.pokedex.PokedexMapper
import com.dexapp.base_presentation.mapper.pokemon.PokemonMapper
import com.dexapp.base_presentation.model.pokedex.PokedexBinding
import com.dexapp.base_presentation.model.pokemon.PokemonBinding
import com.dexapp.base_presentation.utils.extensions.*
import com.dexapp.domain.model.pokedex.Pokedex
import com.dexapp.domain.usecase.generation.GetPokemonByGeneration
import com.dexapp.domain.usecase.generation.SaveGenerationLocal
import com.dexapp.domain.usecase.pokedex.GetPokedex
import com.dexapp.domain.usecase.type.GetPokemonByType
import com.dexapp.domain.usecase.type.SaveTypeLocal
import org.koin.core.KoinComponent

/**
 * Created by Filipi Andrade on 29/03/2020
 */

class PokemonViewModel : ViewModel(), KoinComponent {

    private val getPokedex: GetPokedex by useCase()
    private val getPokemonByType: GetPokemonByType by useCase()
    private val getPokemonByGeneration: GetPokemonByGeneration by useCase()
    private val saveTypeLocal: SaveTypeLocal by useCase()
    private val saveGenerationLocal: SaveGenerationLocal by useCase()

    private val _fetchPokedexViewState by viewState<PokedexBinding>()
    private val _fetchPokedexByTypeTypeViewState by viewState<List<PokemonBinding>>()
    private val _fetchPokedexByGenerationTypeViewState by viewState<List<PokemonBinding>>()

    val fetchPokedexViewState = _fetchPokedexViewState.asLiveData()
    val fetchPokedexByTypeTypeViewState = _fetchPokedexByTypeTypeViewState.asLiveData()
    val fetchPokedexByGenerationTypeViewState = _fetchPokedexByGenerationTypeViewState.asLiveData()

    fun getAllPokemon(offset: Int, previous: Int) {
        if (previous == 0) _fetchPokedexViewState.postLoading()
        getPokedex(
            params = GetPokedex.Params(offset, previous),
            onSuccess = { saveTypeLocal(it) },
            onError = { _fetchPokedexViewState.postError(it) }
        )
    }

    private fun saveTypeLocal(pokedex: Pokedex) {
        saveTypeLocal(
            params = SaveTypeLocal.Params(pokedex.type),
            onSuccess = { saveGenerationLocal(pokedex) },
            onError = { _fetchPokedexViewState.postSuccess(PokedexMapper.fromDomain(pokedex)) }
        )
    }

    private fun saveGenerationLocal(pokedex: Pokedex) {
        saveGenerationLocal(
            params = SaveGenerationLocal.Params(pokedex.generation),
            onSuccess = { _fetchPokedexViewState.postSuccess(PokedexMapper.fromDomain(pokedex)) },
            onError = { _fetchPokedexViewState.postSuccess(PokedexMapper.fromDomain(pokedex)) }
        )
    }

    fun getPokemonByGenenration(id: Int) {
        _fetchPokedexByGenerationTypeViewState.postLoading()
        getPokemonByGeneration(
            params = GetPokemonByGeneration.Params(id),
            onSuccess = {
                _fetchPokedexByGenerationTypeViewState.postSuccess(
                    PokemonMapper.fromDomain(it)
                )
            },
            onError = { _fetchPokedexByGenerationTypeViewState.postError(it) }
        )
    }

    fun getPokemonByType(name: String) {
        _fetchPokedexByTypeTypeViewState.postLoading()
        getPokemonByType(
            params = GetPokemonByType.Params(name),
            onSuccess = {
                _fetchPokedexByTypeTypeViewState.postSuccess(
                    PokemonMapper.fromDomain(it)
                )
            },
            onError = { _fetchPokedexByTypeTypeViewState.postError(it) }
        )
    }

    fun cleanValues() {
        _fetchPokedexViewState.postNeutral()
        _fetchPokedexByTypeTypeViewState.postNeutral()
        _fetchPokedexByGenerationTypeViewState.postNeutral()
    }
}