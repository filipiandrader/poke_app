package com.dexapp.data_remote.model.evolution

import com.google.gson.annotations.SerializedName

/**
 * Created by Filipi Andrade on 03/04/2020
 */

data class EvolutionResponse(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("photo")
    val photo: String? = null
)