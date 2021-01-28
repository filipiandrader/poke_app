package com.pokeapp.domain.usecase.like

import com.pokeapp.domain.core.UseCase
import com.pokeapp.domain.exception.MissingParamsException
import com.pokeapp.domain.model.pokemon.PokemonInfo
import com.pokeapp.domain.repository.PokemonRepository
import kotlinx.coroutines.CoroutineScope

/*
 * Created by Filipi Andrade Rocha on 27/01/2021.
 */

class LikePokemon(
    private val pokemonRepository: PokemonRepository,
    scope: CoroutineScope
) : UseCase<Unit, LikePokemon.Params>(scope) {

    override fun run(params: Params?) = when (params) {
        null -> throw MissingParamsException()
        else -> {
            if (params.pokemon.liked) {
                pokemonRepository.doDislikePokemon(params.pokemon)
            } else {
                pokemonRepository.doLikePokemon(params.pokemon)
            }
        }
    }

    data class Params(val pokemon: PokemonInfo)
}