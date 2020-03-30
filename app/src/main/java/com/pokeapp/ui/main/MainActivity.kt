package com.pokeapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.pokeapp.R
import com.pokeapp.util.setVisible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mNavController = findNavController(R.id.mainNavHostFragment)
        mainBottomNavigationView.setupWithNavController(mNavController)

        mNavController.addOnDestinationChangedListener(this)
    }

    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
        when (destination.id) {
            R.id.homeFragment -> {
                mainToolbar.title = "Pokemon"
                mainBottomNavigationView.setVisible(true)
            }
            R.id.favoriteFragment -> {
                mainToolbar.title = "Pokemon Favoritos"
                mainBottomNavigationView.setVisible(true)
            }
            R.id.pokemonDetailsFragment -> {
                mainToolbar.title = "Detalhes do Pokemon"
                mainBottomNavigationView.setVisible(false)
            }
        }

        setSupportActionBar(mainToolbar)
    }
}
