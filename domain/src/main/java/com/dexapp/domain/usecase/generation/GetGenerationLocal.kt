package com.dexapp.domain.usecase.generation

import com.dexapp.domain.core.UseCase
import com.dexapp.domain.model.generation.Generation
import com.dexapp.domain.repository.GenerationRepository
import kotlinx.coroutines.CoroutineScope

/*
 * Created by Filipi Andrade Rocha on 22/01/2021.
 */

class GetGenerationLocal(
    private val generationRepository: GenerationRepository,
    scope: CoroutineScope
) : UseCase<List<Generation>?, Unit>(scope) {

    override fun run(params: Unit?) = generationRepository.getGenerationLocal()
}