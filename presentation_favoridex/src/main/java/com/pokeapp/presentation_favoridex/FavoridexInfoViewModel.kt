package com.pokeapp.presentation_favoridex

import androidx.lifecycle.ViewModel
import com.pokeapp.base_presentation.mapper.pokemon.PokemonInfoMapper
import com.pokeapp.base_presentation.model.pokemon.PokemonInfoBinding
import com.pokeapp.base_presentation.utils.extensions.*
import com.pokeapp.domain.usecase.like.LikePokemon
import org.koin.core.KoinComponent

/*
 * Created by Filipi Andrade Rocha on 30/01/2021.
 */

class FavoridexInfoViewModel : ViewModel(), KoinComponent {

    private val likePokemon: LikePokemon by useCase()

    private val _fetchLikePokemonViewState by viewState<Unit>()

    val fetchLikePokemonViewState = _fetchLikePokemonViewState.asLiveData()

    fun doLikePokemon(pokemon: PokemonInfoBinding) {
        _fetchLikePokemonViewState.postLoading()
        likePokemon(
            params = LikePokemon.Params(PokemonInfoMapper.toDomain(pokemon)),
            onSuccess = { _fetchLikePokemonViewState.postSuccess(Unit) },
            onError = { _fetchLikePokemonViewState.postError(it) }
        )
    }
}