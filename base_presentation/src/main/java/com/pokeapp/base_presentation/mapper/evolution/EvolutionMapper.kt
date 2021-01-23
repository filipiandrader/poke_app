package com.pokeapp.base_presentation.mapper.evolution

import com.pokeapp.base_presentation.mapper.base.PresentationMapper
import com.pokeapp.base_presentation.model.evolution.EvolutionBinding
import com.pokeapp.domain.model.evolution.Evolution

/*
 * Created by Filipi Andrade Rocha on 22/01/2021.
 */

object EvolutionMapper : PresentationMapper<EvolutionBinding, Evolution> {

    fun listToDomain(presentation: List<EvolutionBinding>) = presentation.map { toDomain(it) }

    fun listFromDomain(domain: List<Evolution>) = domain.map { fromDomain(it) }

    override fun toDomain(presentation: EvolutionBinding) = Evolution(
        name = presentation.name,
        photo = presentation.photo
    )

    override fun fromDomain(domain: Evolution) = EvolutionBinding(
        name = domain.name,
        photo = domain.photo
    )
}