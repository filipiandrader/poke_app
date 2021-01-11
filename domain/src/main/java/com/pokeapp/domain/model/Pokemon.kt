package com.pokeapp.domain.model

/**
 * Created by Filipi Andrade on 30/03/2020
 */

class Pokemon(
    var id: Int,
    var name: String,
    var photo: String,
    var photoShiny: String,
    var generation: String,
    var about: String,
    var height: Int,
    var baseExperience: Int,
    var weight: Int,
    var types: MutableList<Type> = mutableListOf(),
    var abilities: MutableList<Ability> = mutableListOf(),
    var moves: MutableList<Move> = mutableListOf(),
    var stats: MutableList<Stats> = mutableListOf(),
    var evolves: MutableList<Species> = mutableListOf(),
    var liked: Boolean = false
)