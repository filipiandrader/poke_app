package com.pokeapp.data_remote.model.move

import com.google.gson.annotations.SerializedName

/**
 * Created by Filipi Andrade on 03/04/2020
 */

data class MoveResponse(
    @SerializedName("name")
    val name: String? = null
)