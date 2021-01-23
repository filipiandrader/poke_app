package com.pokeapp.base_presentation.model.stats

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Filipi Andrade on 31/03/2020
 */

@Parcelize
class StatsBinding(
    var name: String,
    var baseStat: Int
) : Parcelable