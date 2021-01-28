package com.pokeapp.presentation_pokedex.info

import androidx.lifecycle.ViewModel
import com.pokeapp.base_presentation.mapper.pokemon.PokemonInfoMapper
import com.pokeapp.base_presentation.model.pokemon.PokemonInfoBinding
import com.pokeapp.base_presentation.utils.extensions.*
import com.pokeapp.domain.usecase.like.LikePokemon
import com.pokeapp.domain.usecase.pokemon.GetPokemonInfo
import org.koin.core.KoinComponent

/**
 * Created by Filipi Andrade on 31/03/2020
 */

class PokemonInfoViewModel : ViewModel(), KoinComponent {

    private val getPokemonInfo: GetPokemonInfo by useCase()
    private val likePokemon: LikePokemon by useCase()

    private val _fetchPokemonInfoViewState by viewState<PokemonInfoBinding>()
    private val _fetchLikePokemonViewState by viewState<Unit>()

    val fetchPokemonInfoViewState = _fetchPokemonInfoViewState.asLiveData()
    val fetchLikePokemonViewState = _fetchLikePokemonViewState.asLiveData()

    fun getPokemonInfo(id: Int) {
        _fetchPokemonInfoViewState.postLoading()
        getPokemonInfo(
            params = GetPokemonInfo.Params(id),
            onSuccess = { _fetchPokemonInfoViewState.postSuccess(PokemonInfoMapper.fromDomain(it)) },
            onError = { _fetchPokemonInfoViewState.postError(it) }
        )
    }

    fun doLikePokemon(pokemon: PokemonInfoBinding) {
        _fetchLikePokemonViewState.postLoading()
        likePokemon(
            params = LikePokemon.Params(PokemonInfoMapper.toDomain(pokemon)),
            onSuccess = { _fetchLikePokemonViewState.postSuccess(Unit) },
            onError = { _fetchLikePokemonViewState.postError(it) }
        )
    }
}