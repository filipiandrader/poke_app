package com.pokeapp.base_feature.info.moves

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.pokeapp.base_feature.core.BaseFragment
import com.pokeapp.base_feature.databinding.FragmentMovesBinding
import com.pokeapp.base_feature.info.adapter.MovesAdapter
import com.pokeapp.base_presentation.model.pokemon.PokemonInfoBinding

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