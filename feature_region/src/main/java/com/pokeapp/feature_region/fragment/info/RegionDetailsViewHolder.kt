package com.pokeapp.feature_region.fragment.info

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pokeapp.feature_region.R

/**
 * Created by Filipi Andrade on 31/03/2020
 */
class RegionDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

    val itemNameTextView: TextView = itemView.findViewById(R.id.itemNameTextView)
}