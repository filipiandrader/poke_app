package com.dexapp.base_feature.info.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dexapp.base_feature.core.BaseFragment
import com.dexapp.base_feature.databinding.FragmentAboutBinding
import com.dexapp.base_feature.util.extensions.convertToKilos
import com.dexapp.base_feature.util.extensions.convertToMeter
import com.dexapp.base_feature.util.extensions.putText
import com.dexapp.base_presentation.model.pokemon.PokemonInfoBinding

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
