package com.pokeapp.base_presentation.model.favoridex

import com.pokeapp.base_presentation.model.generation.GenerationBinding
import com.pokeapp.base_presentation.model.pokemon.PokemonInfoBinding
import com.pokeapp.base_presentation.model.type.TypeBinding

/*
 * Created by Filipi Andrade Rocha on 27/01/2021.
 */

class FavoridexBinding(
    var favoridex: List<PokemonInfoBinding> = listOf(),
    var generation: List<GenerationBinding> = listOf(),
    var type: List<TypeBinding> = listOf()
)