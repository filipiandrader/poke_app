package com.pokeapp.di.intent

import androidx.fragment.app.Fragment
import com.pokeapp.feature_favoridex.navigation.info.FavoridexInfoNavigation
import com.pokeapp.feature_favoridex.navigation.main.FavoridexNavigation
import com.pokeapp.intent.navigation.favoridex.info.FavoridexInfoNavigationImpl
import com.pokeapp.intent.navigation.favoridex.main.FavoridexNavigationImpl
import org.koin.dsl.module

/*
 * Created by Filipi Andrade Rocha on 27/01/2021.
 */
 
val intentFavoridexModule = module {

    factory<FavoridexNavigation> { (fragment: Fragment) ->
        FavoridexNavigationImpl(fragment)
    }

    factory<FavoridexInfoNavigation> { (fragment: Fragment) ->
        FavoridexInfoNavigationImpl(fragment)
    }
}