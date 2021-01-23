package com.pokeapp.data_remote.model

import com.google.gson.annotations.SerializedName

/*
 * Created by Filipi Andrade Rocha on 22/01/2021.
 */

data class GenerationResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null
)