package com.pokeapp.domain.model

/**
 * Created by Filipi Andrade on 01/04/2020
 */

data class RegionInfo(
        val id: Int,
        val mainGeneration: String,
        val locations: List<Location> = listOf(),
        val groups: List<Groups> = listOf()
)