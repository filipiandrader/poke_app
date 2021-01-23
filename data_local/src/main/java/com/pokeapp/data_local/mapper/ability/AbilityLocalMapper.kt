package com.pokeapp.data_local.mapper.ability

import com.pokeapp.data_local.model.ability.AbilityLocal
import com.pokeapp.domain.model.ability.Ability

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

object AbilityLocalMapper {

    fun toAbilityList(abilitiesLocal: List<AbilityLocal>) = abilitiesLocal.map { toAbility(it) }

    private fun toAbility(abilityLocal: AbilityLocal) = Ability(
            name = abilityLocal.name
    )

    fun toAbilityLocalList(abilities: List<Ability>) = abilities.map { toAbilityLocal(it) }

    private fun toAbilityLocal(ability: Ability) = AbilityLocal(
            name = ability.name
    )
}