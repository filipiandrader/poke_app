package com.dexapp.intent.navigation.region

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dexapp.base_presentation.model.region.RegionBinding
import com.dexapp.feature_region.fragment.main.RegionFragmentDirections
import com.dexapp.feature_region.navigation.main.RegionNavigation
import com.dexapp.intent.util.navigate

/*
 * Created by Filipi Andrade Rocha on 19/01/2021.
 */

class RegionNavigationImpl(private val fragment: Fragment) : RegionNavigation {

    override fun navigateToInfo(region: RegionBinding) = fragment.navigate(
        RegionFragmentDirections.actionRegionFragmentToRegionInfoFragment(region)
    )

    override fun navigateToHome() {
        fragment.findNavController().popBackStack()
    }
}