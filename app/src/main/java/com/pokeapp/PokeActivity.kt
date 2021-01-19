package com.pokeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.pokeapp.intent.util.safeNavigateUp

class PokeActivity : AppCompatActivity() {

    private lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poke)

        mNavController = findNavController(R.id.mainNavHostFragment)
    }

    override fun onSupportNavigateUp() = mNavController.safeNavigateUp()
}
