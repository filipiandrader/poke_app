package com.dexapp.base_presentation.model.pokedex

import com.dexapp.base_presentation.model.pokemon.PokemonBinding
import com.dexapp.base_presentation.model.type.TypeBinding
import com.dexapp.base_presentation.model.generation.GenerationBinding

/*
 * Created by Filipi Andrade Rocha on 23/01/2021.
 */

class PokedexBinding(
    val pokedex: List<PokemonBinding>,
    val generation: List<GenerationBinding>,
    val type: List<TypeBinding>
)