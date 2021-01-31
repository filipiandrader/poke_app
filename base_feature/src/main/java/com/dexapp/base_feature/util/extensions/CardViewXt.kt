package com.dexapp.base_feature.util.extensions

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.cardview.widget.CardView

/*
 * Created by Filipi Andrade Rocha on 19/01/2021.
 */

fun CardView.setColorFilter(color: Int) {
    background.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
}