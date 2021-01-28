package com.pokeapp.base_presentation.mapper.generation

import com.pokeapp.base_presentation.mapper.base.PresentationMapper
import com.pokeapp.base_presentation.model.generation.GenerationBinding
import com.pokeapp.domain.model.generation.Generation

/*
 * Created by Filipi Andrade Rocha on 22/01/2021.
 */

object GenerationMapper : PresentationMapper<GenerationBinding, Generation> {

    override fun toDomain(presentation: GenerationBinding) = Generation(
        id = presentation.id,
        name = presentation.name,
        region = presentation.region
    )

    override fun fromDomain(domain: Generation) = GenerationBinding(
        id = domain.id,
        name = domain.name,
        region = domain.region
    )
}