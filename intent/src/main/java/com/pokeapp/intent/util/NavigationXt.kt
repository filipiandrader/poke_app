package com.pokeapp.intent.util

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController

fun NavController.safeNavigateUp() = try {
    navigateUp()
} catch (e: Exception) {
    false
}

fun NavController.safePopBackStack(@IdRes destination: Int, popUpInclusive: Boolean) = try {
    popBackStack(destination, popUpInclusive)
} catch (e: Exception) {
    false
}

fun NavController.safeNavigate(directions: NavDirections, navOptions: NavOptions? = null) = try {
    navigate(directions, navOptions)
} catch (e: Exception) {
}

fun NavController.safeNavigate(@IdRes resId: Int, args: Bundle? = null, navOptions: NavOptions? = null) = try {
    navigate(resId, args, navOptions)
} catch (e: Exception) {
}

fun NavController.safeNavigate(directions: NavDirections, navigatorExtras: Navigator.Extras) = try {
    navigate(directions, navigatorExtras)
} catch (e: Exception) {
}

fun Fragment.popBackStack(@IdRes destination: Int, popUpInclusive: Boolean = false) =
        findNavController().safePopBackStack(destination, popUpInclusive)

fun Fragment.navigate(directions: NavDirections, navOptions: NavOptions? = null) =
    findNavController().safeNavigate(directions, navOptions)

fun Fragment.navigate(@IdRes resId: Int, args: Bundle? = null, navOptions: NavOptions? = null) =
        findNavController().safeNavigate(resId, args, navOptions)

fun Fragment.navigate(directions: NavDirections, navigatorExtras: Navigator.Extras) =
        findNavController().safeNavigate(directions, navigatorExtras)
