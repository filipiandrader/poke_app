package com.pokeapp.base_presentation.model.evolution

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Filipi Andrade on 31/03/2020
 */

@Parcelize
class EvolutionBinding(
    var name: String,
    var photo: String
) : Parcelable