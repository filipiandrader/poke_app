package com.dexapp.base_feature.util.extensions

import android.content.res.ColorStateList
import android.widget.ProgressBar

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

fun ProgressBar.putProgress(progress: Int) {
    this.progress = progress
}

fun ProgressBar.setColor(color: Int) {
    this.progressTintList = ColorStateList.valueOf(color)
}