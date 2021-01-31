package com.dexapp.domain.usecase.type

import com.dexapp.domain.core.UseCase
import com.dexapp.domain.exception.EmptyFieldException
import com.dexapp.domain.exception.MissingParamsException
import com.dexapp.domain.model.pokemon.Pokemon
import com.dexapp.domain.repository.TypeRepository
import kotlinx.coroutines.CoroutineScope

/*
 * Created by Filipi Andrade Rocha on 20/01/2021.
 */

class GetPokemonByType(
    private val repository: TypeRepository,
    scope: CoroutineScope
) : UseCase<List<Pokemon>, GetPokemonByType.Params>(scope) {

    override fun run(params: Params?) = when {
        params == null -> throw MissingParamsException()
        params.name.isBlank() -> throw EmptyFieldException()
        else -> repository.getPokemonByType(params.name)
    }

    data class Params(val name: String)
}