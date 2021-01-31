package com.dexapp.base_presentation.model.region

import android.os.Parcelable
import com.dexapp.base_presentation.model.group.GroupsBinding
import com.dexapp.base_presentation.model.location.LocationBinding
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