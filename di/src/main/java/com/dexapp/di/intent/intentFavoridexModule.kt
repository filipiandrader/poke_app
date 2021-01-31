package com.dexapp.di.intent

import androidx.fragment.app.Fragment
import com.dexapp.feature_favoridex.navigation.info.FavoridexInfoNavigation
import com.dexapp.feature_favoridex.navigation.main.FavoridexNavigation
import com.dexapp.intent.navigation.favoridex.info.FavoridexInfoNavigationImpl
import com.dexapp.intent.navigation.favoridex.main.FavoridexNavigationImpl
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