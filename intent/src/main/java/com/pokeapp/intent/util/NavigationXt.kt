package com.pokeapp.intent.util

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

fun NavController.safeNavigateUp() = try {
    navigateUp()
} catch (e: Exception) {
    false
}

fun NavController.safeNavigate(directions: NavDirections, navOptions: NavOptions? = null) = try {
    navigate(directions, navOptions)
} catch (e: Exception) {
}

fun Fragment.navigate(directions: NavDirections, navOptions: NavOptions? = null) =
    findNavController().safeNavigate(directions, navOptions)
