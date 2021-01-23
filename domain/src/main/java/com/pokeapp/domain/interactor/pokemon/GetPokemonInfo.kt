package com.pokeapp.domain.interactor.pokemon

import com.pokeapp.domain.core.UseCase
import com.pokeapp.domain.exception.EmptyFieldException
import com.pokeapp.domain.exception.MissingParamsException
import com.pokeapp.domain.model.PokemonInfo
import com.pokeapp.domain.repository.PokemonRepository
import kotlinx.coroutines.CoroutineScope

/*
 * Created by Filipi Andrade Rocha on 22/01/2021.
 */

class GetPokemonInfo(
    private val repository: PokemonRepository,
    scope: CoroutineScope
) : UseCase<PokemonInfo, GetPokemonInfo.Params>(scope) {

    override fun run(params: Params?) = when {
        params == null -> throw MissingParamsException()
        params.id == -1 -> throw EmptyFieldException()
        else -> repository.getPokemonInfo(params.id)
    }

    data class Params(val id: Int)
}