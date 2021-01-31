package com.dexapp.base_feature.customview.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dexapp.base_feature.databinding.DialogLoadingBinding

/*
 * Created by Filipi Andrade Rocha on 19/01/2021.
 */

class LoadingDialog : BaseFullScreenDialog() {

    private lateinit var binding: DialogLoadingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DialogLoadingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.run {
            dialog?.setCancelable(false)
            setBackgroundDrawableResource(android.R.color.transparent)
            attributes = attributes.run {
                dimAmount = 0f
                this
            }
        }
    }
}