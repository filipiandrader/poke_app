package com.dexapp.domain.usecase.pokedex

import com.dexapp.domain.core.UseCase
import com.dexapp.domain.exception.EmptyFieldException
import com.dexapp.domain.exception.MissingParamsException
import com.dexapp.domain.model.pokedex.Pokedex
import com.dexapp.domain.repository.PokemonRepository
import kotlinx.coroutines.CoroutineScope

/*
 * Created by Filipi Andrade Rocha on 20/01/2021.
 */

class GetPokedex(
    private val repository: PokemonRepository,
    scope: CoroutineScope
) : UseCase<Pokedex, GetPokedex.Params>(scope) {

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