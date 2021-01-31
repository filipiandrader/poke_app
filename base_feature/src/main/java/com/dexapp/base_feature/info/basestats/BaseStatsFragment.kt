package com.dexapp.base_feature.info.basestats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dexapp.base_feature.core.BaseFragment
import com.dexapp.base_feature.databinding.FragmentBaseStatsBinding
import com.dexapp.base_feature.info.adapter.BaseStatsAdapter
import com.dexapp.base_presentation.model.pokemon.PokemonInfoBinding

class BaseStatsFragment : BaseFragment() {

    private lateinit var pokemon: PokemonInfoBinding
    private lateinit var binding: FragmentBaseStatsBinding
    private val baseStatsAdapter = BaseStatsAdapter()

    companion object {
        @JvmStatic
        fun newInstance(pokemon: PokemonInfoBinding) = BaseStatsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("pokemon", pokemon)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentBaseStatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupView() {
        pokemon = checkNotNull(arguments?.getParcelable("pokemon"))
        binding.apply {
            baseStatsAdapter.items = pokemon.stats.toMutableList()
            baseStatsRecyclerView.adapter = baseStatsAdapter
        }
    }
}
