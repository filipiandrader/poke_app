package com.dexapp.presentation_favoridex

import androidx.lifecycle.ViewModel
import com.dexapp.base_presentation.mapper.generation.GenerationMapper
import com.dexapp.base_presentation.mapper.pokemon.PokemonInfoMapper
import com.dexapp.base_presentation.mapper.type.TypeMapper
import com.dexapp.base_presentation.model.favoridex.FavoridexBinding
import com.dexapp.base_presentation.model.pokemon.PokemonInfoBinding
import com.dexapp.base_presentation.utils.extensions.*
import com.dexapp.domain.usecase.generation.GetGenerationLocal
import com.dexapp.domain.usecase.generation.GetPokemonLikedByGeneration
import com.dexapp.domain.usecase.pokedex.GetFavoridex
import com.dexapp.domain.usecase.type.GetPokemonLikedByType
import com.dexapp.domain.usecase.type.GetTypeLocal
import org.koin.core.KoinComponent

/**
 * Created by Filipi Andrade on 31/03/2020
 */

class FavoridexViewModel : ViewModel(), KoinComponent {

    private val getTypeLocal: GetTypeLocal by useCase()
    private val getPokemonLikedByType: GetPokemonLikedByType by useCase()
    private val getGenerationLocal: GetGenerationLocal by useCase()
    private val getPokemonLikedByGeneration: GetPokemonLikedByGeneration by useCase()
    private val getFavoridex: GetFavoridex by useCase()

    private val _fetchFavoridexViewState by viewState<FavoridexBinding>()
    private val _fetchFavoridexByTypeViewState by viewState<List<PokemonInfoBinding>>()
    private val _fetchFavoridexByGenerationViewState by viewState<List<PokemonInfoBinding>>()

    val fetchFavoridexViewState = _fetchFavoridexViewState.asLiveData()
    val fetchFavoridexByTypeViewState = _fetchFavoridexByTypeViewState.asLiveData()
    val fetchFavoridexByGenerationViewState = _fetchFavoridexByGenerationViewState.asLiveData()

    fun getFavoridex() {
        _fetchFavoridexViewState.postLoading()
        getFavoridex(
            onSuccess = {
                val favoridex = FavoridexBinding(
                    favoridex = PokemonInfoMapper.fromDomain(it ?: listOf())
                )
                getGenerations(favoridex)
            },
            onError = { _fetchFavoridexViewState.postError(it) }
        )
    }

    private fun getGenerations(favoridex: FavoridexBinding) {
        getGenerationLocal(
            onSuccess = {
                favoridex.generation = GenerationMapper.fromDomain(it ?: listOf())
                getTypes(favoridex)
            },
            onError = { _fetchFavoridexViewState.postError(it) }
        )
    }

    private fun getTypes(favoridex: FavoridexBinding) {
        getTypeLocal(
            onSuccess = {
                favoridex.type = TypeMapper.fromDomain(it ?: listOf())
                _fetchFavoridexViewState.postSuccess(favoridex)
            },
            onError = { _fetchFavoridexViewState.postError(it) }
        )
    }

    fun getPokemonByGenenration(region: String) {
        _fetchFavoridexByGenerationViewState.postLoading()
        getPokemonLikedByGeneration(
            params = GetPokemonLikedByGeneration.Params(region),
            onSuccess = {
                _fetchFavoridexByGenerationViewState.postSuccess(
                    PokemonInfoMapper.fromDomain(it ?: listOf())
                )
            },
            onError = { _fetchFavoridexByGenerationViewState.postError(it) }
        )
    }

    fun getPokemonByType(type: String) {
        _fetchFavoridexByTypeViewState.postLoading()
        getPokemonLikedByType(
            params = GetPokemonLikedByType.Params(type),
            onSuccess = {
                _fetchFavoridexByTypeViewState.postSuccess(
                    PokemonInfoMapper.fromDomain(it ?: listOf())
                )
            },
            onError = { _fetchFavoridexByTypeViewState.postError(it) }
        )
    }

    fun cleanValeus() {
        _fetchFavoridexViewState.postNeutral()
    }
}