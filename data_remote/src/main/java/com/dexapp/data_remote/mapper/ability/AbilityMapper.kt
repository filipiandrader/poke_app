package com.dexapp.data_remote.mapper.ability

import com.dexapp.data_remote.model.ability.AbilityResponse
import com.dexapp.data_remote.utils.DataRemoteMapper
import com.dexapp.domain.model.ability.Ability

/*
 * Created by Filipi Andrade Rocha on 11/01/2021.
 */

object AbilityMapper : DataRemoteMapper<AbilityResponse, Ability>() {

    fun listToDomain(data: List<AbilityResponse>) = data.map { toDomain(it) }

    override fun toDomain(data: AbilityResponse) = Ability(
        name = data.name ?: "",
    )
}