package com.dexapp.base_feature.customview.emptylist

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.dexapp.base_feature.R

/*
 * Created by Filipi Andrade Rocha on 29/01/2021.
 */

class PlaceholderEmptyList @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private val emptyListMessageTextView: AppCompatTextView
    private val emptyListImageView: AppCompatImageView

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.custom_empty_list, this, true)
            .run {
                emptyListImageView = findViewById(R.id.customEmptyListImageView)
                emptyListMessageTextView = findViewById(R.id.customEmptyListMessageTextView)
            }
    }
}