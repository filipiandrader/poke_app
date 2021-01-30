package com.pokeapp.feature_favoridex.adapter

import android.view.View
import androidx.core.content.ContextCompat
import com.pokeapp.base_feature.core.BaseAdapter
import com.pokeapp.base_feature.core.BaseViewHolder
import com.pokeapp.base_feature.util.enums.PokemonTypeEnum
import com.pokeapp.base_feature.util.extensions.*
import com.pokeapp.base_feature.util.enums.PokemonTypeEnum.DARK
import com.pokeapp.base_presentation.model.pokemon.PokemonInfoBinding
import com.pokeapp.feature_favoridex.R
import kotlinx.android.synthetic.main.item_favoridex.view.*

/*
 * Created by Filipi Andrade Rocha on 29/01/2021.
 */

class FavoridexAdapter(val clickListener: (pokemon: PokemonInfoBinding) -> Unit) :
    BaseAdapter<PokemonInfoBinding, FavoridexAdapter.FavoridexViewHolder>() {

    override fun createViewHolderInstance(view: View) = FavoridexViewHolder(view)

    override val layoutId = R.layout.item_favoridex

    inner class FavoridexViewHolder(private val view: View) :
        BaseViewHolder<PokemonInfoBinding>(view) {

        override fun bind(item: PokemonInfoBinding) {
            view.apply {
                itemPokemonPhotoImageView.loadUrl(item.photo)
                itemPokemonNameTextView.putText(item.name.formatPokemonName())
                itemPokemonIDTextView.putText(item.id.formatPokemonNumber())

                val color = context.getPokemonColor(item.types[0].name)
                itemPokemonCardView.setColorFilter(color)

                val colorID = ContextCompat.getColor(context, R.color.colorIcons)
                when (item.types.size) {
                    1 -> {
                        if (item.types[0].name == DARK.type) {
                            itemPokemonIDTextView.setTextColor(colorID)
                        }
                    }
                    2 -> {
                        if (item.types[1].name == DARK.type) {
                            itemPokemonIDTextView.setTextColor(colorID)
                        }
                    }
                }

                item.types.getOrNull(0).let { firstType ->
                    this.itemPokemonType1TextView.putText(PokemonTypeEnum.match(firstType?.name))
                    this.itemPokemonType1TextView.setVisible(firstType != null)
                }

                item.types.getOrNull(1).let { secondType ->
                    this.itemPokemonType2TextView.putText(PokemonTypeEnum.match(secondType?.name))
                    this.itemPokemonType2TextView.setVisible(secondType != null)
                }

                item.types.getOrNull(2).let { thirdType ->
                    this.itemPokemonType3TextView.putText(PokemonTypeEnum.match(thirdType?.name))
                    this.itemPokemonType3TextView.setVisible(thirdType != null)
                }

                setOnClickListener { clickListener(item) }
            }
        }
    }
}