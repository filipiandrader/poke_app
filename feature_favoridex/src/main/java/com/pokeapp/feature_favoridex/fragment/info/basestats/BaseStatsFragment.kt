package com.pokeapp.feature_favoridex.fragment.info.basestats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afollestad.recyclical.datasource.dataSourceOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.pokeapp.base_feature.core.BaseFragment
import com.pokeapp.base_feature.util.extensions.*
import com.pokeapp.base_presentation.model.pokemon.PokemonInfoBinding
import com.pokeapp.base_presentation.model.stats.StatsBinding
import com.pokeapp.feature_favoridex.R
import com.pokeapp.feature_favoridex.adapter.BaseStatsAdapter
import com.pokeapp.feature_favoridex.databinding.FragmentBaseStatsBinding

class BaseStatsFragment : BaseFragment() {

    private lateinit var pokemon: PokemonInfoBinding
    private lateinit var binding: FragmentBaseStatsBinding
    private lateinit var baseStatsAdapter: BaseStatsAdapter

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
            baseStatsAdapter = BaseStatsAdapter(pokemon.types[0].name)
            baseStatsAdapter.items = pokemon.stats.toMutableList()
            baseStatsRecyclerView.adapter = baseStatsAdapter
        }
    }
}
