package com.pokeapp.feature_pokedex.fragment.info.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pokeapp.base_feature.core.BaseFragment
import com.pokeapp.base_feature.util.extensions.convertToKilos
import com.pokeapp.base_feature.util.extensions.convertToMeter
import com.pokeapp.base_feature.util.extensions.putText
import com.pokeapp.base_presentation.model.PokemonInfoBinding
import com.pokeapp.feature_pokedex.R
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : BaseFragment() {

    private lateinit var pokemon: PokemonInfoBinding

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
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pokemon = checkNotNull(arguments?.getParcelable("pokemon")) as PokemonInfoBinding
        pokemon.let { p ->
            aboutDescriptionTextView.putText(p.description.replace("\n", " "))
            aboutHeightTextView.putText(p.height.convertToMeter())
            aboutWeightTextView.putText(p.weight.convertToKilos())
            aboutBaseExpTextView.putText("${p.baseExperience}")
        }
    }
}
