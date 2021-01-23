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
    @SerializedName("base_experience")
    var baseExperience: Int? = null,
    @SerializedName("height")
    var height: Int? = null,
    @SerializedName("weight")
    var weight: Int? = null,
    @SerializedName("photo")
    var photo: String? = null,
    @SerializedName("photo_shiny")
    var photoShiny: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("generation_name")
    var generationName: String? = null,
    @SerializedName("types")
    var types: List<TypeResponse> = listOf(),
    @SerializedName("abilities")
    var abilities: List<AbilityResponse> = listOf(),
    @SerializedName("moves")
    var moves: List<MoveResponse> = listOf(),
    @SerializedName("stats")
    var stats: List<StatsResponse> = listOf(),
    @SerializedName("first_evolution")
    var firstEvolution: EvolutionResponse? = null,
    @SerializedName("second_evolution")
    var secondEvolution: EvolutionResponse? = null
)
