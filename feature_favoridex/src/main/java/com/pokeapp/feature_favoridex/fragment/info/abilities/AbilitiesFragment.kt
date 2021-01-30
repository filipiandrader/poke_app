package com.pokeapp.feature_favoridex.fragment.info.abilities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.afollestad.recyclical.datasource.dataSourceOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.pokeapp.base_feature.core.BaseFragment
import com.pokeapp.base_feature.util.extensions.formatNameAbility
import com.pokeapp.base_feature.util.extensions.putText
import com.pokeapp.base_presentation.model.ability.AbilityBinding
import com.pokeapp.base_presentation.model.pokemon.PokemonInfoBinding
import com.pokeapp.feature_favoridex.R
import com.pokeapp.feature_favoridex.adapter.AbilitiesAdapter
import com.pokeapp.feature_favoridex.databinding.FragmentAbilitiesBinding

class AbilitiesFragment : BaseFragment() {

    private lateinit var pokemon: PokemonInfoBinding
    private lateinit var binding: FragmentAbilitiesBinding

    private val abilityAdapter = AbilitiesAdapter()

    companion object {
        @JvmStatic
        fun newInstance(pokemon: PokemonInfoBinding) = AbilitiesFragment().apply {
            arguments = Bundle().apply {
                putParcelable("pokemon", pokemon)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAbilitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupView() {
        pokemon = checkNotNull(arguments?.getParcelable("pokemon"))
        binding.apply {
            abilityAdapter.items = pokemon.abilities.toMutableList()
            abilitiesRecyclerView.apply {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = abilityAdapter
            }
        }
    }
}
