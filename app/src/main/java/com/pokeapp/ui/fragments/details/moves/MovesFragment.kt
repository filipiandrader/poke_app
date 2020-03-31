package com.pokeapp.ui.fragments.details.moves

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afollestad.recyclical.datasource.dataSourceOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem

import com.pokeapp.R
import com.pokeapp.presentation.model.Move
import com.pokeapp.presentation.model.Pokemon
import com.pokeapp.util.putText
import kotlinx.android.synthetic.main.fragment_moves.*

/**
 * A simple [Fragment] subclass.
 */
class MovesFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(pokemon: Pokemon) = MovesFragment().apply {
            arguments = Bundle().apply {
                putSerializable("pokemon", pokemon)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_moves, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pokemon = checkNotNull(arguments?.getSerializable("pokemon") as Pokemon)
        movesRecyclerView.setup {
            withDataSource(dataSourceOf(pokemon.moves))
            withItem<Move, MovesViewHolder>(R.layout.item_moves) {
                onBind(::MovesViewHolder) { _, item ->
                    this.itemMoveTextView.putText(item.name)
                }
            }
        }
    }
}