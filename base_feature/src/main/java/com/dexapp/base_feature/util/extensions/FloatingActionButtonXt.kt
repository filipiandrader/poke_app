package com.dexapp.base_feature.util.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.drawable.Drawable
import android.view.animation.OvershootInterpolator
import com.github.clans.fab.FloatingActionMenu

/*
 * Created by Filipi Andrade Rocha on 23/01/2021.
 */
 
fun FloatingActionMenu.createCustomAnimation(pokeballDrawable: Drawable?, closeDrawable: Drawable?) {
    val set = AnimatorSet()
    val scaleOutX = ObjectAnimator.ofFloat(menuIconView, "scaleX", 1.0f, 0.2f)
    val scaleOutY = ObjectAnimator.ofFloat(menuIconView, "scaleY", 1.0f, 0.2f)
    val scaleInX = ObjectAnimator.ofFloat(menuIconView, "scaleX", 0.2f, 1.0f)
    val scaleInY = ObjectAnimator.ofFloat(menuIconView, "scaleY", 0.2f, 1.0f)

    scaleOutX.duration = 50
    scaleOutY.duration = 50
    scaleInX.duration = 150
    scaleInY.duration = 150

    scaleInX.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator) {
            val img = when (isOpened) {
                true -> pokeballDrawable
                false -> closeDrawable
            }
            menuIconView.setImageDrawable(img)
        }
    })

    set.play(scaleOutX).with(scaleOutY)
    set.play(scaleInX).with(scaleInY).after(scaleOutX)
    set.interpolator = OvershootInterpolator(2f)

    iconToggleAnimatorSet = set
}