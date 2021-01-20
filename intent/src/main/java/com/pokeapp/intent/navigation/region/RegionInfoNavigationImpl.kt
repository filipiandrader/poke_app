package com.pokeapp.intent.navigation.region

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pokeapp.feature_region.fragment.info.RegionInfoFragmentArgs
import com.pokeapp.feature_region.navigation.info.RegionInfoNavigation

/*
 * Created by Filipi Andrade Rocha on 19/01/2021.
 */

class RegionInfoNavigationImpl(private val fragment: Fragment) : RegionInfoNavigation {

    private val arguments = fragment.navArgs<RegionInfoFragmentArgs>().value

    override val region = arguments.region

    override fun navigateToRegion() {
        fragment.findNavController().popBackStack()
    }
}