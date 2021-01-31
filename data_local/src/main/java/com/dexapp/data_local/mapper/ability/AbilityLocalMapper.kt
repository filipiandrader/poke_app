package com.dexapp.data_local.mapper.ability

import com.dexapp.data_local.model.ability.AbilityLocal
import com.dexapp.data_local.mapper.base.DataLocalMapper
import com.dexapp.domain.model.ability.Ability

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

object AbilityLocalMapper : DataLocalMapper<AbilityLocal, Ability> {

    override fun toLocal(domain: Ability) = AbilityLocal(
        name = domain.name
    )

    override fun fromLocal(local: AbilityLocal) = Ability(
        name = local.name
    )
}