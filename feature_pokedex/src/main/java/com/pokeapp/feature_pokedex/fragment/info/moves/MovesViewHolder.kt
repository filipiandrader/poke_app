package com.pokeapp.feature_pokedex.fragment.info.moves

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pokeapp.feature_pokedex.R

/**
 * Created by Filipi Andrade on 31/03/2020
 */
class MovesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

    val itemMoveTextView: TextView = itemView.findViewById(R.id.itemMoveTextView)
}