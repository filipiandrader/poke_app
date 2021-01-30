package com.pokeapp.base_feature.info.adapter

import android.view.View
import com.pokeapp.base_feature.R
import com.pokeapp.base_feature.core.BaseAdapter
import com.pokeapp.base_feature.core.BaseViewHolder
import com.pokeapp.base_feature.util.extensions.formatNameMove
import com.pokeapp.base_feature.util.extensions.putText
import com.pokeapp.base_presentation.model.move.MoveBinding
import kotlinx.android.synthetic.main.item_moves.view.*

/*
 * Created by Filipi Andrade Rocha on 29/01/2021.
 */

class MovesAdapter : BaseAdapter<MoveBinding, MovesAdapter.MovesViewHolder>() {

    override fun createViewHolderInstance(view: View) = MovesViewHolder(view)

    override val layoutId = R.layout.item_moves

    inner class MovesViewHolder(private val view: View): BaseViewHolder<MoveBinding>(view) {

        override fun bind(item: MoveBinding) {
            view.apply {
                itemMoveTextView.putText(item.name.formatNameMove())
            }
        }
    }
}