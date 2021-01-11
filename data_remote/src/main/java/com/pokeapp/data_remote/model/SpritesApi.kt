package com.pokeapp.data_remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Filipi Andrade on 03/04/2020
 */
class SpritesApi(
    @SerializedName("photo")
    var photo: String = "",
    @SerializedName("photo_shiny")
    var photoShiny: String = ""
)