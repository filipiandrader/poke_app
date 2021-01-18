package com.pokeapp.data_remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Filipi Andrade on 01/04/2020
 */

data class RegionInfoResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("main_generation")
    val mainGeneration: String? = null,
    @SerializedName("locations")
    var locations: List<LocationResponse> = listOf(),
    @SerializedName("groups")
    var groups: List<GroupsResponse> = listOf()
)