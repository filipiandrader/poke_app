package com.dexapp.base_presentation.mapper.location

import com.dexapp.base_presentation.mapper.base.PresentationMapper
import com.dexapp.base_presentation.model.location.LocationBinding
import com.dexapp.domain.model.location.Location

/*
 * Created by Filipi Andrade Rocha on 19/01/2021.
 */

object LocationMapper : PresentationMapper<LocationBinding, Location> {

    fun listFromDomain(locations: List<Location>) = locations.map { fromDomain(it) }

    fun listToDomain(locations: List<LocationBinding>) = locations.map { toDomain(it) }

    override fun toDomain(presentation: LocationBinding) = Location(
        name = presentation.name
    )

    override fun fromDomain(domain: Location) = LocationBinding(
        name = domain.name
    )
}