package com.pokeapp.data_remote.mapper

import com.pokeapp.data_remote.model.RegionApi
import com.pokeapp.data_remote.model.RegionResponse
import com.pokeapp.data_remote.utils.DataRemoteMapper
import com.pokeapp.domain.model.Region

/*
 * Created by Filipi Andrade Rocha on 11/01/2021.
 */

object RegionMapper : DataRemoteMapper<RegionResponse, List<Region>>() {

    override fun toDomain(data: RegionResponse) = data.results.map { toDomain(it) }

    private fun toDomain(data: RegionApi) = Region(
        name = data.name ?: "",
    )
}