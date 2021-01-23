package com.pokeapp.domain.model.pokedex

import com.pokeapp.domain.model.pokemon.Pokemon
import com.pokeapp.domain.model.type.Type
import com.pokeapp.domain.model.generation.Generation

/*
 * Created by Filipi Andrade Rocha on 23/01/2021.
 */

data class Pokedex(
    val pokedex: List<Pokemon>,
    val generation: List<Generation>,
    val type: List<Type>
)
