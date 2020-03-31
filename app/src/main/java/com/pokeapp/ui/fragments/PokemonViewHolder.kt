package com.pokeapp.ui.fragments

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pokeapp.R

/**
 * Created by Filipi Andrade on 29/03/2020
 */

class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val itemPokemonImageView : ImageView = itemView.findViewById(R.id.itemPokemonImageView)
    val itemPokemonTextView : TextView = itemView.findViewById(R.id.itemPokemonTextView)
}