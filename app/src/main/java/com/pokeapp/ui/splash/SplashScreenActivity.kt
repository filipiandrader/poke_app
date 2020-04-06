package com.pokeapp.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pokeapp.R
import com.pokeapp.ui.main.MainActivity
import org.jetbrains.anko.startActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toMain()
    }

    private fun toMain() {
        startActivity<MainActivity>()
        finishAffinity()
    }
}
