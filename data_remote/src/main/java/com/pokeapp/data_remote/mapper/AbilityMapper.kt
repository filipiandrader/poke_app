package com.pokeapp.data_remote.mapper

import com.pokeapp.data_remote.model.AbilityResponse
import com.pokeapp.data_remote.utils.DataRemoteMapper
import com.pokeapp.domain.model.Ability

/*
 * Created by Filipi Andrade Rocha on 11/01/2021.
 */

object AbilityMapper : DataRemoteMapper<AbilityResponse, Ability>() {

    fun listToDomain(data: List<AbilityResponse>) = data.map { toDomain(it) }

    override fun toDomain(data: AbilityResponse) = Ability(
        name = data.name ?: "",
    )
}