package com.pokeapp.base_presentation.mapper.generation

import com.pokeapp.base_presentation.mapper.base.PresentationMapper
import com.pokeapp.base_presentation.model.GenerationBinding
import com.pokeapp.domain.model.Generation

/*
 * Created by Filipi Andrade Rocha on 22/01/2021.
 */

object GenerationMapper : PresentationMapper<GenerationBinding, Generation> {

    fun listToDomain(presentation: List<GenerationBinding>) = presentation.map { toDomain(it) }

    fun listFromDomain(domain: List<Generation>) = domain.map { fromDomain(it) }

    override fun toDomain(presentation: GenerationBinding) = Generation(
        id = presentation.id,
        name = presentation.name
    )

    override fun fromDomain(domain: Generation) = GenerationBinding(
        id = domain.id,
        name = domain.name
    )
}