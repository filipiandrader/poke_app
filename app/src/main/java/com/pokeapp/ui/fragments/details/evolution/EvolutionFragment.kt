package com.pokeapp.ui.fragments.details.evolution

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.pokeapp.R
import com.pokeapp.base_presentation.model.PokemonBinding
import com.pokeapp.base_feature.util.extensions.putText
import com.pokeapp.base_feature.util.extensions.setVisible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_evolution.*


class EvolutionFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(pokemon: PokemonBinding) = EvolutionFragment().apply {
            arguments = Bundle().apply {
                putParcelable("pokemon", pokemon)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_evolution, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pokemon = checkNotNull(arguments?.getSerializable("pokemon") as PokemonBinding)
        pokemon.let {
            if (pokemon.evolves.isNotEmpty()) {
                if (pokemon.evolves.size == 1) {
                    evolutionArrowImageView.setVisible(false)
                    evolutionImageView1.setVisible(false)
                    firstEvolutionImageView.setVisible(false)
                    firstEvolutionNameTextView.setVisible(false)
                    evolutionImageView3.setVisible(false)
                    secondEvolutionImageView.setVisible(false)
                    secondEvolutionNameTextView.setVisible(false)

                    evolutionImageView2.setVisible(true)
                    uniqueEvolutionImageView.setVisible(true)
                    uniqueEvolutionNameTextView.setVisible(true)

                    Picasso.get().load(pokemon.evolves[0].photo).into(uniqueEvolutionImageView)
                    uniqueEvolutionNameTextView.putText(pokemon.evolves[0].name)
                } else {
                    evolutionImageView2.setVisible(false)
                    uniqueEvolutionImageView.setVisible(false)
                    uniqueEvolutionNameTextView.setVisible(false)

                    evolutionArrowImageView.setVisible(true)
                    evolutionImageView1.setVisible(true)
                    firstEvolutionImageView.setVisible(true)
                    firstEvolutionNameTextView.setVisible(true)
                    evolutionImageView3.setVisible(true)
                    secondEvolutionImageView.setVisible(true)
                    secondEvolutionNameTextView.setVisible(true)

                    for (i in pokemon.evolves.indices) {
                        if (i == 0) {
                            Picasso.get().load(pokemon.evolves[i].photo).into(firstEvolutionImageView)
                            firstEvolutionNameTextView.putText(pokemon.evolves[i].name)
                        } else if (i == 1) {
                            Picasso.get().load(pokemon.evolves[i].photo).into(secondEvolutionImageView)
                            secondEvolutionNameTextView.putText(pokemon.evolves[i].name)
                        }
                    }
                }
            } else {
                evolutionArrowImageView.setVisible(false)
                evolutionImageView1.setVisible(false)
                firstEvolutionImageView.setVisible(false)
                firstEvolutionNameTextView.setVisible(false)
                evolutionImageView3.setVisible(false)
                secondEvolutionImageView.setVisible(false)
                secondEvolutionNameTextView.setVisible(false)
                evolutionImageView2.setVisible(false)
                uniqueEvolutionImageView.setVisible(false)
                uniqueEvolutionNameTextView.setVisible(false)
            }
        }
    }

}
