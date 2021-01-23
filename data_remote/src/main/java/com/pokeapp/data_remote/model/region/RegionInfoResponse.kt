package com.pokeapp.data_remote.model.region

import com.google.gson.annotations.SerializedName
import com.pokeapp.data_remote.model.groups.GroupsResponse
import com.pokeapp.data_remote.model.location.LocationResponse

/**
 * Created by Filipi Andrade on 01/04/2020
 */

data class RegionInfoResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("main_generation")
    val mainGeneration: String? = null,
    @SerializedName("locations")
    var locations: List<LocationResponse>,
    @SerializedName("groups")
    var groups: List<GroupsResponse>
)