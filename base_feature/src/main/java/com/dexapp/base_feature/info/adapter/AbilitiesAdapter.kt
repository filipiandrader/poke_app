package com.dexapp.base_feature.info.adapter

import android.view.View
import com.dexapp.base_feature.R
import com.dexapp.base_feature.core.BaseAdapter
import com.dexapp.base_feature.core.BaseViewHolder
import com.dexapp.base_feature.util.extensions.formatNameAbility
import com.dexapp.base_feature.util.extensions.putText
import com.dexapp.base_presentation.model.ability.AbilityBinding
import kotlinx.android.synthetic.main.item_ability.view.*

/*
 * Created by Filipi Andrade Rocha on 29/01/2021.
 */

class AbilitiesAdapter : BaseAdapter<AbilityBinding, AbilitiesAdapter.AbilitiesViewHolder>() {

    override fun createViewHolderInstance(view: View) = AbilitiesViewHolder(view)

    override val layoutId = R.layout.item_ability

    inner class AbilitiesViewHolder(private val view: View) : BaseViewHolder<AbilityBinding>(view) {

        override fun bind(item: AbilityBinding) {
            view.apply {
                itemAbilityTextView.putText(item.name.formatNameAbility())
            }
        }
    }
}