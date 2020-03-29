package com.pokeapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.pokeapp.R
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
            R.id.homeFragment -> mainToolbar.title = "Pokemon"
            R.id.favoriteFragment -> mainToolbar.title = "Pokekon Favoritos"
        }

        setSupportActionBar(mainToolbar)
    }
}
