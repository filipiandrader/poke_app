package com.dexapp.domain.usecase.generation

import com.dexapp.domain.core.UseCase
import com.dexapp.domain.exception.EmptyFieldException
import com.dexapp.domain.exception.MissingParamsException
import com.dexapp.domain.model.pokemon.Pokemon
import com.dexapp.domain.repository.GenerationRepository
import kotlinx.coroutines.CoroutineScope

/*
 * Created by Filipi Andrade Rocha on 22/01/2021.
 */

class GetPokemonByGeneration(
    private val generationRepository: GenerationRepository,
    scope: CoroutineScope
) : UseCase<List<Pokemon>, GetPokemonByGeneration.Params>(scope) {

    override fun run(params: Params?) = when {
        params == null -> throw MissingParamsException()
        params.id == -1 -> throw EmptyFieldException()
        else -> generationRepository.getPokemonByGeneration(params.id)
    }

    data class Params(val id: Int)
}