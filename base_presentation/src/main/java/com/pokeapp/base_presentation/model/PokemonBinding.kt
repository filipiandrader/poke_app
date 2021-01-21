package com.pokeapp.base_presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Filipi Andrade on 29/03/2020
 */

@Parcelize
class PokemonBinding(
    var id: Int,
    var name: String,
    var photo: String,
    var photoShiny: String,
    var generationName: String,
    var types: List<TypeBinding>,
    var liked: Boolean
) : Parcelable