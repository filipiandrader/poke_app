package com.pokeapp.domain.model.pokemon

import com.pokeapp.domain.model.stats.Stats
import com.pokeapp.domain.model.type.Type
import com.pokeapp.domain.model.ability.Ability
import com.pokeapp.domain.model.evolution.Evolution
import com.pokeapp.domain.model.move.Move

/**
 * Created by Filipi Andrade on 30/03/2020
 */

data class PokemonInfo(
    val id: Int,
    val name: String,
    val photo: String,
    val photoShiny: String,
    val generationName: String,
    val description: String,
    val height: Int,
    val baseExperience: Int,
    val weight: Int,
    val types: List<Type> = listOf(),
    val abilities: List<Ability> = listOf(),
    val moves: List<Move> = listOf(),
    val stats: List<Stats> = listOf(),
    val evolution: List<Evolution> = listOf(),
    val liked: Boolean = false
)