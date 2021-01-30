package com.pokeapp.feature_favoridex.fragment.info.moves

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.afollestad.recyclical.datasource.dataSourceOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.pokeapp.base_feature.core.BaseFragment
import com.pokeapp.base_feature.util.extensions.formatNameMove
import com.pokeapp.base_feature.util.extensions.putText
import com.pokeapp.base_presentation.model.move.MoveBinding
import com.pokeapp.base_presentation.model.pokemon.PokemonInfoBinding
import com.pokeapp.feature_favoridex.R
import com.pokeapp.feature_favoridex.adapter.MovesAdapter
import com.pokeapp.feature_favoridex.databinding.FragmentMovesBinding

class MovesFragment : BaseFragment() {

    private lateinit var pokemon: PokemonInfoBinding
    private lateinit var binding: FragmentMovesBinding
    private val movesAdapter = MovesAdapter()

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
    ): View {
        binding = FragmentMovesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupView() {
        pokemon = checkNotNull(arguments?.getParcelable("pokemon"))
        binding.apply {
            movesAdapter.items = pokemon.moves.toMutableList()
            movesRecyclerView.apply {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = movesAdapter
            }
        }
    }
}