package com.pokeapp.ui.fragments

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.pokeapp.R

/**
 * Created by Filipi Andrade on 29/03/2020
 */

class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val itemPokemonPhotoImageView: ImageView = itemView.findViewById(R.id.itemPokemonPhotoImageView)
    val itemPokemonNameTextView: TextView = itemView.findViewById(R.id.itemPokemonNameTextView)
    val itemPokemonIDTextView: TextView = itemView.findViewById(R.id.itemPokemonIDTextView)
    val itemPokemonType1TextView: TextView = itemView.findViewById(R.id.itemPokemonType1TextView)
    val itemPokemonType2TextView: TextView = itemView.findViewById(R.id.itemPokemonType2TextView)
    val itemPokemonType3TextView: TextView = itemView.findViewById(R.id.itemPokemonType3TextView)
    val itemPokemonCardView: CardView = itemView.findViewById(R.id.itemPokemonCardView)
}