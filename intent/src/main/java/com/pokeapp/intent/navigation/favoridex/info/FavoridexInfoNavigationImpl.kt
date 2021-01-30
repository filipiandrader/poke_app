package com.pokeapp.intent.navigation.favoridex.info

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pokeapp.feature_favoridex.fragment.info.FavoridexInfoFragmentArgs
import com.pokeapp.feature_favoridex.navigation.info.FavoridexInfoNavigation

/*
 * Created by Filipi Andrade Rocha on 29/01/2021.
 */

class FavoridexInfoNavigationImpl(private val fragment: Fragment) : FavoridexInfoNavigation {

    private val arguments = fragment.navArgs<FavoridexInfoFragmentArgs>().value
    override val pokemon = arguments.pokemon

    override fun navigateToFavoridex() {
        fragment.findNavController().popBackStack()
    }
}