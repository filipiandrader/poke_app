package com.pokeapp.presentation_favoridex

import androidx.lifecycle.ViewModel
import com.pokeapp.base_presentation.mapper.generation.GenerationMapper
import com.pokeapp.base_presentation.mapper.pokemon.PokemonInfoMapper
import com.pokeapp.base_presentation.mapper.type.TypeMapper
import com.pokeapp.base_presentation.model.favoridex.FavoridexBinding
import com.pokeapp.base_presentation.utils.extensions.*
import com.pokeapp.domain.usecase.generation.GetGenerationLocal
import com.pokeapp.domain.usecase.pokedex.GetFavoridex
import com.pokeapp.domain.usecase.type.GetTypeLocal
import org.koin.core.KoinComponent

/**
 * Created by Filipi Andrade on 31/03/2020
 */

class FavoridexViewModel : ViewModel(), KoinComponent {

    private val getTypeLocal: GetTypeLocal by useCase()
    private val getGenerationLocal: GetGenerationLocal by useCase()
    private val getFavoridex: GetFavoridex by useCase()

    private val _fetchFavoridexViewState by viewState<FavoridexBinding>()

    val fetchFavoridexViewState = _fetchFavoridexViewState.asLiveData()

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
    }

    fun getPokemonByType(type: String) {
    }

    fun cleanValeus() {
        _fetchFavoridexViewState.postNeutral()
    }
}