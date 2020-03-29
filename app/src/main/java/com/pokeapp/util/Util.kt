package com.pokeapp.util

import android.view.View
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.pokeapp.data.remote.model.PokemonApi
import com.pokeapp.presentation.model.Pokemon

/**
 * Created by Filipi Andrade Rocha on 29/01/2020
 */

fun View.setVisible(visible: Boolean) = if (visible) {
    this.visibility = View.VISIBLE
} else {
    this.visibility = View.GONE
}

fun View.setEnable(enable: Boolean) {
    this.isEnabled = enable
}

fun TextView.putText(text: String) {
    this.text = text
}

fun TextInputEditText.getTextString() = this.text.toString()

fun TextInputLayout.setErrorText(error: String) {
//    this.isErrorEnabled = true
    this.error = error
}