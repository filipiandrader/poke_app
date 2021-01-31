package com.dexapp.data_remote.model.pokedex

import com.google.gson.annotations.SerializedName
import com.dexapp.data_remote.model.generation.GenerationResponse
import com.dexapp.data_remote.model.pokemon.PokemonResponse
import com.dexapp.data_remote.model.type.TypeResponse

/*
 * Created by Filipi Andrade Rocha on 23/01/2021.
 */

data class PokedexResponse(
    @SerializedName("pokedex")
    val pokedex: List<PokemonResponse>,
    @SerializedName("generation")
    val generation: List<GenerationResponse>,
    @SerializedName("type")
    val type: List<TypeResponse>
)
