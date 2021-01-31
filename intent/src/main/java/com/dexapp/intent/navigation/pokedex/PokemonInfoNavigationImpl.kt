package com.dexapp.intent.navigation.pokedex

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dexapp.feature_pokedex.fragment.info.PokemonInfoFragmentArgs
import com.dexapp.feature_pokedex.navigation.PokemonInfoNavigation

/*
 * Created by Filipi Andrade Rocha on 22/01/2021.
 */

class PokemonInfoNavigationImpl(private val fragment: Fragment): PokemonInfoNavigation {

    private val arguments = fragment.navArgs<PokemonInfoFragmentArgs>().value

    override val pokemon = arguments.pokemon

    override fun navigateToPokedex() {
        fragment.findNavController().popBackStack()
    }
}