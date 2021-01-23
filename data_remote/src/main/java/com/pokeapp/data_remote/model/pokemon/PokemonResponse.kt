package com.pokeapp.data_remote.model.pokemon

import com.google.gson.annotations.SerializedName
import com.pokeapp.data_remote.model.type.TypeResponse

data class PokemonResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("photo")
    val photo: String? = null,
    @SerializedName("photo_shiny")
    val photoShiny: String? = null,
    @SerializedName("generation_name")
    val generationName: String? = null,
    @SerializedName("types")
    val types: List<TypeResponse> = listOf()
)