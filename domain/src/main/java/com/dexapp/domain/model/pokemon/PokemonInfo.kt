package com.dexapp.domain.model.pokemon

import com.dexapp.domain.model.stats.Stats
import com.dexapp.domain.model.type.Type
import com.dexapp.domain.model.ability.Ability
import com.dexapp.domain.model.evolution.Evolution
import com.dexapp.domain.model.move.Move

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