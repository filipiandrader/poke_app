package com.pokeapp.feature_pokedex.fragment.info.evolution

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pokeapp.base_feature.core.BaseFragment
import com.pokeapp.base_feature.util.extensions.loadUrl
import com.pokeapp.base_feature.util.extensions.putText
import com.pokeapp.base_feature.util.extensions.setGone
import com.pokeapp.base_feature.util.extensions.setVisible
import com.pokeapp.base_presentation.model.PokemonInfoBinding
import com.pokeapp.feature_pokedex.databinding.FragmentEvolutionBinding

class EvolutionFragment : BaseFragment() {

    private lateinit var pokemon: PokemonInfoBinding
    private lateinit var binding: FragmentEvolutionBinding

    companion object {
        @JvmStatic
        fun newInstance(pokemon: PokemonInfoBinding) = EvolutionFragment().apply {
            arguments = Bundle().apply {
                putParcelable("pokemon", pokemon)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentEvolutionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupView() {
        pokemon = checkNotNull(arguments?.getParcelable("pokemon"))
        pokemon.let {
            binding.apply {
                if (pokemon.evolves.isNotEmpty()) {
                    if (pokemon.evolves.size == 1) {
                        evolutionArrowImageView.setGone()
                        evolutionImageView1.setGone()
                        firstEvolutionImageView.setGone()
                        firstEvolutionNameTextView.setGone()
                        evolutionImageView3.setGone()
                        secondEvolutionImageView.setGone()
                        secondEvolutionNameTextView.setGone()

                        evolutionImageView2.setVisible()
                        uniqueEvolutionImageView.setVisible()
                        uniqueEvolutionNameTextView.setVisible()

                        uniqueEvolutionImageView.loadUrl(pokemon.evolves[0].photo)
                        uniqueEvolutionNameTextView.putText(pokemon.evolves[0].name)
                    } else {
                        evolutionImageView2.setGone()
                        uniqueEvolutionImageView.setGone()
                        uniqueEvolutionNameTextView.setGone()

                        evolutionArrowImageView.setVisible()
                        evolutionImageView1.setVisible()
                        firstEvolutionImageView.setVisible()
                        firstEvolutionNameTextView.setVisible()
                        evolutionImageView3.setVisible()
                        secondEvolutionImageView.setVisible()
                        secondEvolutionNameTextView.setVisible()

                        for (i in pokemon.evolves.indices) {
                            if (i == 0) {
                                firstEvolutionImageView.loadUrl(pokemon.evolves[i].photo)
                                firstEvolutionNameTextView.putText(pokemon.evolves[i].name)
                            } else if (i == 1) {
                                secondEvolutionImageView.loadUrl(pokemon.evolves[i].photo)
                                secondEvolutionNameTextView.putText(pokemon.evolves[i].name)
                            }
                        }
                    }
                } else {
                    evolutionArrowImageView.setGone()
                    evolutionImageView1.setGone()
                    firstEvolutionImageView.setGone()
                    firstEvolutionNameTextView.setGone()
                    evolutionImageView3.setGone()
                    secondEvolutionImageView.setGone()
                    secondEvolutionNameTextView.setGone()
                    evolutionImageView2.setGone()
                    uniqueEvolutionImageView.setGone()
                    uniqueEvolutionNameTextView.setGone()
                }
            }
        }
    }
}
