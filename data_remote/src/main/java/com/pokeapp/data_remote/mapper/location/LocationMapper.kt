package com.pokeapp.data_remote.mapper.location

import com.pokeapp.data_remote.model.location.LocationResponse
import com.pokeapp.data_remote.utils.DataRemoteMapper
import com.pokeapp.domain.model.location.Location

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

object LocationMapper : DataRemoteMapper<LocationResponse, Location>() {

    fun listToDomain(data: List<LocationResponse>) = data.map { toDomain(it) }

    override fun toDomain(data: LocationResponse) = Location(
        name = data.name ?: ""
    )
}