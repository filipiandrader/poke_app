package com.pokeapp.feature_region.navigation.main

import com.pokeapp.base_presentation.model.RegionBinding

/*
 * Created by Filipi Andrade Rocha on 19/01/2021.
 */

interface RegionNavigation {

    fun navigateToInfo(region: RegionBinding)

    fun navigateToHome()
}