package com.dexapp.feature_region.adapter

import android.view.View
import com.dexapp.base_feature.core.BaseAdapter
import com.dexapp.base_feature.core.BaseViewHolder
import com.dexapp.base_feature.util.extensions.formatNameRegion
import com.dexapp.base_feature.util.extensions.putText
import com.dexapp.base_presentation.model.group.GroupsBinding
import com.dexapp.feature_region.R
import kotlinx.android.synthetic.main.item_region_info.view.*

/*
 * Created by Filipi Andrade Rocha on 19/01/2021.
 */

class GroupAdapter : BaseAdapter<GroupsBinding, GroupAdapter.GroupViewHolder>() {

    override fun createViewHolderInstance(view: View) = GroupViewHolder(view)

    override val layoutId = R.layout.item_region_info

    inner class GroupViewHolder(private val view: View) : BaseViewHolder<GroupsBinding>(view) {

        override fun bind(item: GroupsBinding) {
            view.apply {
                itemNameTextView.putText(item.name.formatNameRegion())
            }
        }
    }
}