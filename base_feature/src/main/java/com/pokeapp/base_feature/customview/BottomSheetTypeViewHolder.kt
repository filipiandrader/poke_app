package com.pokeapp.base_feature.customview

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.pokeapp.base_feature.R

/**
 * Created by Filipi Andrade on 02/04/2020
 */

class BottomSheetTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val itemTypeNameTextView: TextView = itemView.findViewById(R.id.itemTypeNameTextView)
    val itemTypeCardView: CardView = itemView.findViewById(R.id.itemTypeCardView)
}