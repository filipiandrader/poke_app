package com.pokeapp.feature_favoridex.adapter

import android.view.View
import com.pokeapp.base_feature.core.BaseAdapter
import com.pokeapp.base_feature.core.BaseViewHolder
import com.pokeapp.base_feature.util.extensions.*
import com.pokeapp.base_presentation.model.stats.StatsBinding
import com.pokeapp.feature_favoridex.R
import kotlinx.android.synthetic.main.item_base_stats.view.*

/*
 * Created by Filipi Andrade Rocha on 29/01/2021.
 */

class BaseStatsAdapter(private val type: String) : BaseAdapter<StatsBinding, BaseStatsAdapter.BaseStatsViewHolder>() {

    override fun createViewHolderInstance(view: View) = BaseStatsViewHolder(view)

    override val layoutId = R.layout.item_base_stats

    inner class BaseStatsViewHolder(private val view: View) : BaseViewHolder<StatsBinding>(view) {

        override fun bind(item: StatsBinding) {
            view.apply {
                baseStatsLabelTextView.putText(
                    item.name.formatNameStats(context)
                )
                baseStatsTextView.putText("${item.baseStat}")
                if (item.name == "total") {
                    baseStatsTotalProgressBar.putProgress(item.baseStat)
                    baseStatsTotalProgressBar.setVisible()
                    baseStatsProgressBar.setGone()

                    val color = context.getPokemonColor(type)
                    baseStatsTotalProgressBar.setColor(color)
                } else {
                    baseStatsProgressBar.putProgress(item.baseStat)
                    baseStatsProgressBar.setVisible()
                    baseStatsTotalProgressBar.setGone()
                }
            }
        }
    }
}