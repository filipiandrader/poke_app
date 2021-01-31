package com.dexapp.base_feature.util.extensions

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.constraintlayout.widget.ConstraintLayout

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */
 
fun ConstraintLayout.setColorFilter(color: Int) {
    background.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
}