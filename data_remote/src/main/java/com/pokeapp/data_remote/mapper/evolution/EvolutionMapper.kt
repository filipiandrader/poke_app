package com.pokeapp.data_remote.mapper.evolution

import com.pokeapp.data_remote.model.evolution.EvolutionResponse
import com.pokeapp.data_remote.utils.DataRemoteMapper
import com.pokeapp.domain.model.evolution.Evolution

/*
 * Created by Filipi Andrade Rocha on 11/01/2021.
 */

object EvolutionMapper : DataRemoteMapper<EvolutionResponse, Evolution>() {

    fun listToDomain(data: List<EvolutionResponse>) = data.map { toDomain(it) }

    override fun toDomain(data: EvolutionResponse) = Evolution(
        name = data.name ?: "",
        photo = data.photo ?: ""
    )
}