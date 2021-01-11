package com.pokeapp.data_remote.mapper

import com.pokeapp.data_remote.model.AbilityApi
import com.pokeapp.data_remote.model.SpeciesApi
import com.pokeapp.data_remote.utils.DataRemoteMapper
import com.pokeapp.domain.model.Ability
import com.pokeapp.domain.model.Species

/*
 * Created by Filipi Andrade Rocha on 11/01/2021.
 */

object EvolvesMapper : DataRemoteMapper<MutableList<SpeciesApi>, MutableList<Species>>() {

    override fun toDomain(data: MutableList<SpeciesApi>) = data.map { toDomain(it) }.toMutableList()

    private fun toDomain(data: SpeciesApi) = Species(
        name = data.name ?: "",
        photo = data.photo ?: ""
    )
}