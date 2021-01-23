package com.pokeapp.feature_pokedex.fragment.info.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pokeapp.base_feature.core.BaseFragment
import com.pokeapp.base_feature.util.extensions.convertToKilos
import com.pokeapp.base_feature.util.extensions.convertToMeter
import com.pokeapp.base_feature.util.extensions.putText
import com.pokeapp.base_presentation.model.pokemon.PokemonInfoBinding
import com.pokeapp.feature_pokedex.databinding.FragmentAboutBinding

class AboutFragment : BaseFragment() {

    private lateinit var pokemon: PokemonInfoBinding
    private lateinit var binding: FragmentAboutBinding

    companion object {
        @JvmStatic
        fun newInstance(pokemon: PokemonInfoBinding) = AboutFragment().apply {
            arguments = Bundle().apply {
                putParcelable("pokemon", pokemon)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupView() {
        pokemon = checkNotNull(arguments?.getParcelable("pokemon"))
        pokemon.let { p ->
            binding.apply {
                aboutDescriptionTextView.putText(p.description.replace("\n", " "))
                aboutHeightTextView.putText(p.height.convertToMeter())
                aboutWeightTextView.putText(p.weight.convertToKilos())
                aboutBaseExpTextView.putText("${p.baseExperience}")
            }
        }
    }
}
