package com.pokeapp.domain.model.region

import com.pokeapp.domain.model.groups.Groups
import com.pokeapp.domain.model.location.Location

/**
 * Created by Filipi Andrade on 01/04/2020
 */

data class RegionInfo(
        val id: Int,
        val mainGeneration: String,
        val locations: List<Location> = listOf(),
        val groups: List<Groups> = listOf()
)