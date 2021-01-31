package com.dexapp.data_remote.model.stats

import com.google.gson.annotations.SerializedName

/**
 * Created by Filipi Andrade on 03/04/2020
 */

class StatsResponse(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("base_stat")
    val baseStat: Int? = null
)