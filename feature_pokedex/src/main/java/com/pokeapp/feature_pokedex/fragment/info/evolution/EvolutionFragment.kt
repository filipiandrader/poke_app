package com.pokeapp.feature_pokedex.fragment.info.evolution

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pokeapp.base_feature.core.BaseFragment
import com.pokeapp.base_feature.util.extensions.*
import com.pokeapp.base_presentation.model.pokemon.PokemonInfoBinding
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
                if (pokemon.evolution.isNotEmpty()) {
                    if (pokemon.evolution.size == 2) {
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

                        uniqueEvolutionImageView.loadUrl(pokemon.evolution[1].photo)
                        uniqueEvolutionNameTextView.putText(pokemon.evolution[1].name.formatPokemonName())
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

                        for (i in pokemon.evolution.indices) {
                            if (i == 1) {
                                firstEvolutionImageView.loadUrl(pokemon.evolution[i].photo)
                                firstEvolutionNameTextView.putText(pokemon.evolution[i].name.formatPokemonName())
                            } else if (i == 2) {
                                secondEvolutionImageView.loadUrl(pokemon.evolution[i].photo)
                                secondEvolutionNameTextView.putText(pokemon.evolution[i].name.formatPokemonName())
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
