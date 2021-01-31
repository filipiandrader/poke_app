package com.dexapp.base_feature.info.abilities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.dexapp.base_feature.core.BaseFragment
import com.dexapp.base_feature.databinding.FragmentAbilitiesBinding
import com.dexapp.base_feature.info.adapter.AbilitiesAdapter
import com.dexapp.base_presentation.model.pokemon.PokemonInfoBinding

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
