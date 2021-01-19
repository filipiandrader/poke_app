package com.pokeapp.di.intent

import androidx.fragment.app.Fragment
import com.pokeapp.feature_region.navigation.main.RegionNavigation
import com.pokeapp.intent.navigation.region.RegionNavigationImpl
import org.koin.dsl.module

/*
 * Created by Filipi Andrade Rocha on 19/01/2021.
 */
 
val intentRegionModule = module {

    factory<RegionNavigation> { (fragment: Fragment) ->
        RegionNavigationImpl(fragment)
    }
}