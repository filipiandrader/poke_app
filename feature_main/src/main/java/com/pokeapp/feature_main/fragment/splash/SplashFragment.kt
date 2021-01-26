package com.pokeapp.feature_main.fragment.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pokeapp.base_feature.core.BaseFragment
import com.pokeapp.base_feature.util.delegateproperties.navDirections
import com.pokeapp.base_feature.util.extensions.changeStatusBarColor
import com.pokeapp.base_feature.util.extensions.convertColor
import com.pokeapp.feature_main.R
import com.pokeapp.feature_main.databinding.FragmentSplashBinding
import com.pokeapp.feature_main.navigation.splash.SplashNavigation
import java.util.*

class SplashFragment : BaseFragment() {

    private val navigation: SplashNavigation by navDirections()
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        changeStatusBarColor(getColor())
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun getColor() = requireContext().convertColor(R.color.colorAccent)

    override fun setupView() {
        Timer().schedule(object : TimerTask() {
            override fun run() {
                navigation.navigateToMain()
            }
        }, 3000)
    }
}