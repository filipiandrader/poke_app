package com.dexapp.domain.usecase.generation

import com.dexapp.domain.core.UseCase
import com.dexapp.domain.exception.EmptyFieldException
import com.dexapp.domain.exception.MissingParamsException
import com.dexapp.domain.model.pokemon.PokemonInfo
import com.dexapp.domain.repository.GenerationRepository
import kotlinx.coroutines.CoroutineScope

/*
 * Created by Filipi Andrade Rocha on 22/01/2021.
 */

class GetPokemonLikedByGeneration(
    private val generationRepository: GenerationRepository,
    scope: CoroutineScope
) : UseCase<List<PokemonInfo>?, GetPokemonLikedByGeneration.Params>(scope) {

    override fun run(params: Params?) = when {
        params == null -> throw MissingParamsException()
        params.name.isBlank() -> throw EmptyFieldException()
        else -> generationRepository.getPokemonLikedByGeneration(params.name)
    }

    data class Params(val name: String)
}