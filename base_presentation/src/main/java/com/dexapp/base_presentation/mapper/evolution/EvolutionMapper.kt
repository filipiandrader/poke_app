package com.dexapp.base_presentation.mapper.evolution

import com.dexapp.base_presentation.mapper.base.PresentationMapper
import com.dexapp.base_presentation.model.evolution.EvolutionBinding
import com.dexapp.domain.model.evolution.Evolution

/*
 * Created by Filipi Andrade Rocha on 22/01/2021.
 */

object EvolutionMapper : PresentationMapper<EvolutionBinding, Evolution> {

    override fun toDomain(presentation: EvolutionBinding) = Evolution(
        name = presentation.name,
        photo = presentation.photo
    )

    override fun fromDomain(domain: Evolution) = EvolutionBinding(
        name = domain.name,
        photo = domain.photo
    )
}