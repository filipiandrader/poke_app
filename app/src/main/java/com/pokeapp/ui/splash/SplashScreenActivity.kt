package com.pokeapp.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
