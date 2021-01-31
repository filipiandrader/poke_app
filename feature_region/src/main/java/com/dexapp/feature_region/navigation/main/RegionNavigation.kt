package com.dexapp.feature_region.navigation.main

import com.dexapp.base_presentation.model.region.RegionBinding

/*
 * Created by Filipi Andrade Rocha on 19/01/2021.
 */

interface RegionNavigation {

    fun navigateToInfo(region: RegionBinding)

    fun navigateToHome()
}