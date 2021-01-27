package com.pokeapp.domain.usecase.generation

import com.pokeapp.domain.core.UseCase
import com.pokeapp.domain.exception.MissingParamsException
import com.pokeapp.domain.model.generation.Generation
import com.pokeapp.domain.repository.GenerationRepository
import kotlinx.coroutines.CoroutineScope

/*
 * Created by Filipi Andrade Rocha on 26/01/2021.
 */

class SaveGenerationLocal(
    private val generationRepository: GenerationRepository,
    scope: CoroutineScope
) : UseCase<Unit, SaveGenerationLocal.Params>(scope) {

    override fun run(params: Params?) = when (params) {
        null -> throw MissingParamsException()
        else -> generationRepository.insertGenerationLocal(params.generation)
    }

    data class Params(val generation: List<Generation>)
}