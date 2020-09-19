package com.pokeapp.ui.fragments

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pokeapp.R

/**
 * Created by Filipi Andrade on 02/04/2020
 */
class BottomSheetGenerationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val itemGenerationNameTextView: TextView = itemView.findViewById(R.id.itemGenerationNameTextView)
    val itemGenerationPhotoImageView: ImageView = itemView.findViewById(R.id.itemGenerationPhotoImageView)
}