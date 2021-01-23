package com.pokeapp.domain.interactor.generation

import com.pokeapp.domain.core.UseCase
import com.pokeapp.domain.exception.EmptyFieldException
import com.pokeapp.domain.exception.MissingParamsException
import com.pokeapp.domain.model.Pokemon
import com.pokeapp.domain.repository.GenerationRepository
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