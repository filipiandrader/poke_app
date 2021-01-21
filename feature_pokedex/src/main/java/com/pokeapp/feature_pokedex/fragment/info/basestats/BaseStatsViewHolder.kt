package com.pokeapp.feature_pokedex.fragment.info.basestats

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pokeapp.feature_pokedex.R

/**
 * Created by Filipi Andrade on 31/03/2020
 */
class BaseStatsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val baseStatsLabelTextView: TextView = itemView.findViewById(R.id.baseStatsLabelTextView)
    val baseStatsTextView: TextView = itemView.findViewById(R.id.baseStatsTextView)
    val baseStatsProgressBar: ProgressBar = itemView.findViewById(R.id.baseStatsProgressBar)
    val baseStatsTotalProgressBar: ProgressBar = itemView.findViewById(R.id.baseStatsTotalProgressBar)
}