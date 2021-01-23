package com.pokeapp.data_remote.mapper.region

import com.pokeapp.data_remote.model.region.RegionResponse
import com.pokeapp.data_remote.utils.DataRemoteMapper
import com.pokeapp.domain.model.region.Region

/*
 * Created by Filipi Andrade Rocha on 11/01/2021.
 */

object RegionMapper : DataRemoteMapper<RegionResponse, Region>() {

    fun listToDomain(data: List<RegionResponse>) = data.map { toDomain(it) }

    override fun toDomain(data: RegionResponse) = Region(
        name = data.name ?: "",
    )
}