package com.pokeapp.domain.model

/**
 * Created by Filipi Andrade on 30/03/2020
 */

data class PokemonInfo(
        val id: Int,
        val name: String,
        val photo: String,
        val photoShiny: String,
        val generation: String,
        val about: String,
        val height: Int,
        val baseExperience: Int,
        val weight: Int,
        val types: List<Type> = listOf(),
        val abilities: List<Ability> = listOf(),
        val moves: List<Move> = listOf(),
        val stats: List<Stats> = listOf(),
        val evolves: List<Species> = listOf(),
        val liked: Boolean = false
)