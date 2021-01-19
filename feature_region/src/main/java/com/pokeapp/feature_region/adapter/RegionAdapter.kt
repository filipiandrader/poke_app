package com.pokeapp.feature_region.adapter

import android.view.View
import com.pokeapp.base_feature.core.BaseAdapter
import com.pokeapp.base_feature.core.BaseViewHolder
import com.pokeapp.base_feature.util.extensions.getCardViewColor
import com.pokeapp.base_feature.util.extensions.putText
import com.pokeapp.base_feature.util.extensions.setColorFilter
import com.pokeapp.base_feature.util.extensions.uppercase
import com.pokeapp.base_presentation.model.RegionBinding
import com.pokeapp.feature_region.R
import kotlinx.android.synthetic.main.item_region.view.*

/*
 * Created by Filipi Andrade Rocha on 19/01/2021.
 */

class RegionAdapter(val clickListener: (region: RegionBinding) -> Unit) :
    BaseAdapter<RegionBinding, RegionAdapter.RegionViewHolder>() {

    override fun createViewHolderInstance(view: View) = RegionViewHolder(view)

    override val layoutId = R.layout.item_region

    inner class RegionViewHolder(private val view: View) : BaseViewHolder<RegionBinding>(view) {

        override fun bind(item: RegionBinding) {
            view.apply {
                itemRegionNameTextView.putText(item.name.uppercase())
                itemRegionCardView.apply {
                    val color = context.getCardViewColor(item.name)
                    setColorFilter(color)
                    setOnClickListener { clickListener(item) }
                }
            }
        }
    }
}