package com.pokeapp.base_presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Filipi Andrade on 01/04/2020
 */

@Parcelize
class RegionInfoBinding(
    var id: Int,
    var mainGeneration: String,
    var locations: List<LocationBinding>,
    var groups: List<GroupsBinding>
) : Parcelable