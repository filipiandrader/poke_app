package com.pokeapp.base_feature.customview.bottomsheet

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pokeapp.base_feature.R

/**
 * Created by Filipi Andrade on 02/04/2020
 */
class BottomSheetGenerationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val itemGenerationNameTextView: TextView = itemView.findViewById(R.id.itemGenerationNameTextView)
    val itemGenerationPhotoImageView: ImageView = itemView.findViewById(R.id.itemGenerationPhotoImageView)
}