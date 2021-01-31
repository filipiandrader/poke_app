package com.dexapp.base_feature.util.extensions

import android.view.View

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.setGone() {
    this.visibility = View.GONE
}

fun View.setVisible(visible: Boolean) = if (visible) {
    this.visibility = View.VISIBLE
} else {
    this.visibility = View.GONE
}