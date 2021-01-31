package com.dexapp.base_presentation.mapper.ability

import com.dexapp.base_presentation.mapper.base.PresentationMapper
import com.dexapp.base_presentation.model.ability.AbilityBinding
import com.dexapp.domain.model.ability.Ability

/*
 * Created by Filipi Andrade Rocha on 22/01/2021.
 */

object AbilityMapper : PresentationMapper<AbilityBinding, Ability> {

    override fun toDomain(presentation: AbilityBinding) = Ability(name = presentation.name)

    override fun fromDomain(domain: Ability) = AbilityBinding(name = domain.name)
}