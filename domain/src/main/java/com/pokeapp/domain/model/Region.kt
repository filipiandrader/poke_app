package com.pokeapp.domain.model

/**
 * Created by Filipi Andrade on 01/04/2020
 */

class Region(var name: String,
             var mainGeneration: String = "",
             var locations: MutableList<Location> = mutableListOf(),
             var groups: MutableList<Groups> = mutableListOf())