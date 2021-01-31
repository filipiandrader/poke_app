package com.dexapp.domain.usecase.generation

import com.dexapp.domain.core.UseCase
import com.dexapp.domain.exception.MissingParamsException
import com.dexapp.domain.model.generation.Generation
import com.dexapp.domain.repository.GenerationRepository
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