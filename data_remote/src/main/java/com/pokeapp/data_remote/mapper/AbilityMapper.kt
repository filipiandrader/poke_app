package com.pokeapp.data_remote.mapper

import com.pokeapp.data_remote.model.AbilityApi
import com.pokeapp.data_remote.utils.DataRemoteMapper
import com.pokeapp.domain.model.Ability

/*
 * Created by Filipi Andrade Rocha on 11/01/2021.
 */

object AbilityMapper : DataRemoteMapper<MutableList<AbilityApi>, MutableList<Ability>>() {

    override fun toDomain(data: MutableList<AbilityApi>) = data.map { toDomain(it) }.toMutableList()

    private fun toDomain(data: AbilityApi) = Ability(
        name = data.name ?: "",
    )
}