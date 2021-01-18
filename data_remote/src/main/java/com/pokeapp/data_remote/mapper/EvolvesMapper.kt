package com.pokeapp.data_remote.mapper

import com.pokeapp.data_remote.model.EvolutionResponse
import com.pokeapp.data_remote.utils.DataRemoteMapper
import com.pokeapp.domain.model.Species

/*
 * Created by Filipi Andrade Rocha on 11/01/2021.
 */

object EvolvesMapper : DataRemoteMapper<EvolutionResponse, Species>() {

    fun listToDomain(data: List<EvolutionResponse>) = data.map { toDomain(it) }

    override fun toDomain(data: EvolutionResponse) = Species(
        name = data.name ?: "",
        photo = data.photo ?: ""
    )
}