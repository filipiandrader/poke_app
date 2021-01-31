package com.dexapp.feature_region.adapter

import android.view.View
import com.dexapp.base_feature.core.BaseAdapter
import com.dexapp.base_feature.core.BaseViewHolder
import com.dexapp.base_feature.util.extensions.formatNameRegion
import com.dexapp.base_feature.util.extensions.putText
import com.dexapp.base_presentation.model.location.LocationBinding
import com.dexapp.feature_region.R
import kotlinx.android.synthetic.main.item_region_info.view.*

/*
 * Created by Filipi Andrade Rocha on 19/01/2021.
 */

class LocationAdapter : BaseAdapter<LocationBinding, LocationAdapter.LocationViewHolder>() {

    override fun createViewHolderInstance(view: View) = LocationViewHolder(view)

    override val layoutId = R.layout.item_region_info

    inner class LocationViewHolder(private val view: View) : BaseViewHolder<LocationBinding>(view) {

        override fun bind(item: LocationBinding) {
            view.apply {
                itemNameTextView.putText(item.name.formatNameRegion())
            }
        }
    }
}