package com.pokeapp.domain.interactor.pokedex

import com.pokeapp.domain.core.UseCase
import com.pokeapp.domain.exception.EmptyFieldException
import com.pokeapp.domain.exception.MissingParamsException
import com.pokeapp.domain.model.Pokemon
import com.pokeapp.domain.repository.PokemonRepository
import kotlinx.coroutines.CoroutineScope

/*
 * Created by Filipi Andrade Rocha on 20/01/2021.
 */

class GetPokedex(
    private val repository: PokemonRepository,
    scope: CoroutineScope
) : UseCase<List<Pokemon>, GetPokedex.Params>(scope) {

    override fun run(params: Params?) = when {
        params == null -> throw MissingParamsException()
        params.offset == -1 || params.previous == -1 -> throw EmptyFieldException()
        else -> repository.getAllPokemons(params.offset, params.previous)
    }

    data class Params(
        val offset: Int,
        val previous: Int
    )
}