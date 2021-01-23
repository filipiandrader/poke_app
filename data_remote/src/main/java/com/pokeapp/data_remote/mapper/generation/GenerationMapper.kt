package com.pokeapp.data_remote.mapper.generation

import com.pokeapp.data_remote.model.generation.GenerationResponse
import com.pokeapp.data_remote.utils.DataRemoteMapper
import com.pokeapp.domain.model.generation.Generation

/*
 * Created by Filipi Andrade Rocha on 22/01/2021.
 */

object GenerationMapper : DataRemoteMapper<GenerationResponse, Generation>() {

    fun listToDomain(data: List<GenerationResponse>) = data.map { toDomain(it) }

    override fun toDomain(data: GenerationResponse) = Generation(
        id = data.id ?: -1,
        name = data.name ?: ""
    )
}