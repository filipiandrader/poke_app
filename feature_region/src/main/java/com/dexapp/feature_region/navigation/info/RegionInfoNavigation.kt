package com.dexapp.feature_region.navigation.info

import com.dexapp.base_presentation.model.region.RegionBinding

/*
 * Created by Filipi Andrade Rocha on 19/01/2021.
 */

interface RegionInfoNavigation {

    val region: RegionBinding

    fun navigateToRegion()
}