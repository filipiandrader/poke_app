package com.pokeapp.data_remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Filipi Andrade on 01/04/2020
 */

data class GroupsResponse(
    @SerializedName("name")
    val name: String? = null
)