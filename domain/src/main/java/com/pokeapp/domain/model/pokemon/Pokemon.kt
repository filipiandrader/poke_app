package com.pokeapp.domain.model.pokemon

import com.pokeapp.domain.model.type.Type

/**
 * Created by Filipi Andrade on 30/03/2020
 */

data class Pokemon(
    val id: Int,
    val name: String,
    val photo: String,
    val photoShiny: String,
    val generationName: String,
    val types: List<Type> = listOf(),
    var liked: Boolean = false
)