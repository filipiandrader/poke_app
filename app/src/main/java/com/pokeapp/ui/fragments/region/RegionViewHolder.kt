package com.pokeapp.ui.fragments.region

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.pokeapp.R

/**
 * Created by Filipi Andrade on 01/04/2020
 */
class RegionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val itemRegionNameTextView: TextView = itemView.findViewById(R.id.itemRegionNameTextView)
    val itemRegionCardView: CardView = itemView.findViewById(R.id.itemRegionCardView)
}