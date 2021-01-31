package com.dexapp.feature_pokedex.adapter

import android.view.View
import androidx.core.content.ContextCompat
import com.dexapp.base_feature.core.BaseAdapter
import com.dexapp.base_feature.core.BaseViewHolder
import com.dexapp.base_feature.util.enums.PokemonTypeEnum
import com.dexapp.base_feature.util.enums.PokemonTypeEnum.DARK
import com.dexapp.base_feature.util.extensions.*
import com.dexapp.base_presentation.model.pokemon.PokemonBinding
import com.dexapp.feature_pokedex.R
import kotlinx.android.synthetic.main.item_pokemon.view.*

/*
 * Created by Filipi Andrade Rocha on 20/01/2021.
 */

class PokedexAdapter(val clickListener: (pokemon: PokemonBinding) -> Unit) :
    BaseAdapter<PokemonBinding, PokedexAdapter.PokedexViewHolder>() {

    override fun createViewHolderInstance(view: View) = PokedexViewHolder(view)

    override val layoutId = R.layout.item_pokemon

    inner class PokedexViewHolder(private val view: View) : BaseViewHolder<PokemonBinding>(view) {

        override fun bind(item: PokemonBinding) {
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