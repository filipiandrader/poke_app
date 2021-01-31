package com.dexapp.data_remote.utils

import com.google.gson.annotations.SerializedName

/**
 * Created by Filipi Andrade Rocha on 03/06/2020.
 */

data class GenericResponse<T>(
    @SerializedName("data")
    val data: T?
)