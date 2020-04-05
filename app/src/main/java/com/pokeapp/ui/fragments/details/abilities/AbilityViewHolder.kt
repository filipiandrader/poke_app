package com.pokeapp.ui.fragments.details.abilities

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pokeapp.R

/**
 * Created by Filipi Andrade on 04/04/2020
 */
class AbilityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

    val itemAbilityTextView: TextView = itemView.findViewById(R.id.itemAbilityTextView)
}