package com.pokeapp.domain.model

/**
 * Created by Filipi Andrade on 30/03/2020
 */

data class Pokemon(
        val id: Int,
        val name: String,
        val photo: String,
        val photoShiny: String,
        val types: List<Type> = listOf(),
        val liked: Boolean = false
)