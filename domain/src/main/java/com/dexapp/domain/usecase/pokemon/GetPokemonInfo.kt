package com.dexapp.domain.usecase.pokemon

import com.dexapp.domain.core.UseCase
import com.dexapp.domain.exception.EmptyFieldException
import com.dexapp.domain.exception.MissingParamsException
import com.dexapp.domain.model.pokemon.PokemonInfo
import com.dexapp.domain.repository.PokemonRepository
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