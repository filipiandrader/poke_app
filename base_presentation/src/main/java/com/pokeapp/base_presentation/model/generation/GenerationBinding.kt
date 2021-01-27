package com.pokeapp.base_presentation.model.generation

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Filipi Andrade on 02/04/2020
 */

@Parcelize
class GenerationBinding(
    var id: Int,
    var name: String,
    var region: String
) : Parcelable