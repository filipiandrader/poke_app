package com.pokeapp.feature_pokedex.fragment.info.moves

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.afollestad.recyclical.datasource.dataSourceOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.pokeapp.base_feature.util.extensions.formatNameMove
import com.pokeapp.base_feature.util.extensions.putText
import com.pokeapp.base_presentation.model.MoveBinding
import com.pokeapp.base_presentation.model.PokemonBinding
import com.pokeapp.base_presentation.model.PokemonInfoBinding
import com.pokeapp.feature_pokedex.R
import kotlinx.android.synthetic.main.fragment_moves.*

class MovesFragment : Fragment() {

    private lateinit var pokemon: PokemonInfoBinding

    companion object {
        @JvmStatic
        fun newInstance(pokemon: PokemonInfoBinding) = MovesFragment().apply {
            arguments = Bundle().apply {
                putParcelable("pokemon", pokemon)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_moves, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pokemon = checkNotNull(arguments?.getParcelable("pokemon"))
        movesRecyclerView.setup {
            withLayoutManager(GridLayoutManager(view.context, 2))
            withDataSource(dataSourceOf(pokemon.moves))
            withItem<MoveBinding, MovesViewHolder>(R.layout.item_moves) {
                onBind(::MovesViewHolder) { _, item ->
                    this.itemMoveTextView.putText(item.name.formatNameMove())
                }
            }
        }
    }
}