package com.pokeapp.base_feature.customview.bottomsheet.generation

import android.view.View
import com.pokeapp.base_feature.R
import com.pokeapp.base_feature.core.BaseAdapter
import com.pokeapp.base_feature.core.BaseViewHolder
import com.pokeapp.base_feature.util.enums.GenerationEnum
import com.pokeapp.base_feature.util.extensions.putText
import com.pokeapp.base_presentation.model.generation.GenerationBinding
import kotlinx.android.synthetic.main.item_generation.view.*

/*
 * Created by Filipi Andrade Rocha on 25/01/2021.
 */

class GenerationAdapter(val clickListener: (generation: GenerationBinding) -> Unit) :
    BaseAdapter<GenerationBinding, GenerationAdapter.GenerationViewHolder>() {

    override fun createViewHolderInstance(view: View) = GenerationViewHolder(view)

    override val layoutId = R.layout.item_generation

    inner class GenerationViewHolder(private val view: View) :
        BaseViewHolder<GenerationBinding>(view) {

        override fun bind(item: GenerationBinding) {
            view.apply {
                val name = context.getString(GenerationEnum.getName(item.name))
                itemGenerationNameTextView.putText(name)

                val icon = GenerationEnum.getIcon(item.name)
                itemGenerationPhotoImageView.setImageResource(icon)

                setOnClickListener { clickListener(item) }
            }
        }
    }
}