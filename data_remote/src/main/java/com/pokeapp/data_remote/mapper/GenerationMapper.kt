package com.pokeapp.data_remote.mapper

import com.pokeapp.data_remote.model.GenerationResponse
import com.pokeapp.data_remote.utils.DataRemoteMapper
import com.pokeapp.domain.model.Generation

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