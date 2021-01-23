package com.pokeapp.base_presentation.model.type

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Filipi Andrade on 01/04/2020
 */

@Parcelize
data class TypeBinding(
        var name: String
) : Parcelable