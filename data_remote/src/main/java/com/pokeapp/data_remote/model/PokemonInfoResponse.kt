package com.pokeapp.data_remote.model

import com.google.gson.annotations.SerializedName

/*
 * Created by Filipi Andrade Rocha on 11/01/2021.
 */

data class PokemonInfoResponse(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("photo")
    var photo: String? = null,
    @SerializedName("photo_shiny")
    var photoShiny: String? = null,
    @SerializedName("generation")
    var generation: String? = null,
    @SerializedName("about")
    var about: String? = null,
    @SerializedName("height")
    var height: Int? = null,
    @SerializedName("base_experience")
    var baseExperience: Int? = null,
    @SerializedName("weight")
    var weight: Int? = null,
    @SerializedName("types")
    var types: MutableList<TypeApi> = mutableListOf(),
    @SerializedName("abilities")
    var abilities: MutableList<AbilityApi> = mutableListOf(),
    @SerializedName("moves")
    var moves: MutableList<MoveApi> = mutableListOf(),
    @SerializedName("stats")
    var stats: MutableList<StatsApi> = mutableListOf(),
    @SerializedName("evolves")
    var evolves: MutableList<SpeciesApi> = mutableListOf()
)
