package com.dexapp.domain.model.pokedex

import com.dexapp.domain.model.pokemon.Pokemon
import com.dexapp.domain.model.type.Type
import com.dexapp.domain.model.generation.Generation

/*
 * Created by Filipi Andrade Rocha on 23/01/2021.
 */

data class Pokedex(
    val pokedex: List<Pokemon>,
    val generation: List<Generation>,
    val type: List<Type>
)
