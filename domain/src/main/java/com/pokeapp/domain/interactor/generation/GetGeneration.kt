package com.pokeapp.domain.interactor.generation

import com.pokeapp.domain.core.UseCase
import com.pokeapp.domain.model.generation.Generation
import com.pokeapp.domain.repository.GenerationRepository
import kotlinx.coroutines.CoroutineScope

/*
 * Created by Filipi Andrade Rocha on 22/01/2021.
 */

class GetGeneration(
    private val generationRepository: GenerationRepository,
    scope: CoroutineScope
) : UseCase<List<Generation>, Unit>(scope) {

    override fun run(params: Unit?) = generationRepository.getGeneration()
}