package com.pokeapp.data_remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Filipi Andrade on 03/04/2020
 */

class StatsApi(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("base_state")
    var baseState: Int? = null
)