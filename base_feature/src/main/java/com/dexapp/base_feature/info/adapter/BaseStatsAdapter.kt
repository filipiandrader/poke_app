package com.dexapp.base_feature.info.adapter

import android.view.View
import com.dexapp.base_feature.R
import com.dexapp.base_feature.core.BaseAdapter
import com.dexapp.base_feature.core.BaseViewHolder
import com.dexapp.base_feature.util.enums.BaseStatsEnum
import com.dexapp.base_feature.util.enums.BaseStatsEnum.TOTAL
import com.dexapp.base_feature.util.extensions.*
import com.dexapp.base_presentation.model.stats.StatsBinding
import kotlinx.android.synthetic.main.item_base_stats.view.*

/*
 * Created by Filipi Andrade Rocha on 29/01/2021.
 */

class BaseStatsAdapter : BaseAdapter<StatsBinding, BaseStatsAdapter.BaseStatsViewHolder>() {

    override fun createViewHolderInstance(view: View) = BaseStatsViewHolder(view)

    override val layoutId = R.layout.item_base_stats

    inner class BaseStatsViewHolder(private val view: View) : BaseViewHolder<StatsBinding>(view) {

        override fun bind(item: StatsBinding) {
            view.apply {
                baseStatsLabelTextView.putText(BaseStatsEnum.getStats(context, item.name))
                baseStatsTextView.putText("${item.baseStat}")
                if (item.name == TOTAL.stats) {
                    baseStatsTotalProgressBar.putProgress(item.baseStat)
                    baseStatsTotalProgressBar.setVisible()
                    baseStatsProgressBar.setGone()

                    val color = context.convertColor(R.color.colorPrimaryText)
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