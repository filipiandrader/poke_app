package com.dexapp.domain.usecase.type

import com.dexapp.domain.core.UseCase
import com.dexapp.domain.exception.EmptyFieldException
import com.dexapp.domain.exception.MissingParamsException
import com.dexapp.domain.model.pokemon.PokemonInfo
import com.dexapp.domain.repository.TypeRepository
import kotlinx.coroutines.CoroutineScope

/*
 * Created by Filipi Andrade Rocha on 29/01/2021.
 */

class GetPokemonLikedByType(
    private val repository: TypeRepository,
    scope: CoroutineScope
) : UseCase<List<PokemonInfo>?, GetPokemonLikedByType.Params>(scope) {

    override fun run(params: Params?) = when {
        params == null -> throw MissingParamsException()
        params.name.isBlank() -> throw EmptyFieldException()
        else -> repository.getPokemonLikedByType(params.name)
    }

    data class Params(val name: String)
}