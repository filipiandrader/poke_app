package com.dexapp.base_feature.customview.bottomsheet.type

import android.view.View
import com.dexapp.base_feature.R
import com.dexapp.base_feature.core.BaseAdapter
import com.dexapp.base_feature.core.BaseViewHolder
import com.dexapp.base_feature.util.enums.PokemonTypeEnum
import com.dexapp.base_feature.util.extensions.getPokemonColor
import com.dexapp.base_feature.util.extensions.putText
import com.dexapp.base_feature.util.extensions.setColorFilter
import com.dexapp.base_presentation.model.type.TypeBinding
import kotlinx.android.synthetic.main.item_type.view.*

/*
 * Created by Filipi Andrade Rocha on 25/01/2021.
 */

class TypeAdapter(val clickListener: (type: TypeBinding) -> Unit) :
BaseAdapter<TypeBinding, TypeAdapter.TypeViewHolder>(){

    override fun createViewHolderInstance(view: View) = TypeViewHolder(view)

    override val layoutId = R.layout.item_type

    inner class TypeViewHolder(private val view: View) : BaseViewHolder<TypeBinding>(view) {

        override fun bind(item: TypeBinding) {
            view.apply {
                itemTypeNameTextView.putText(PokemonTypeEnum.match(item.name))

                val color = context.getPokemonColor(item.name)
                itemTypeCardView.setColorFilter(color)

                setOnClickListener { clickListener(item) }
            }
        }
    }
}