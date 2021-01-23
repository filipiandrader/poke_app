package com.pokeapp.data_remote.model.type

import com.google.gson.annotations.SerializedName

/**
 * Created by Filipi Andrade on 04/04/2020
 */

data class TypeResponse(
    @SerializedName("name")
    val name: String? = null
)