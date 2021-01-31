package com.dexapp.base_feature.customview.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dexapp.base_feature.customview.base.BaseDialogFragment
import com.dexapp.base_feature.databinding.CustomDialogBinding
import com.dexapp.base_feature.util.extensions.putText

/*
 * Created by Filipi Andrade Rocha on 31/01/2021.
 */

class MessageDialog(private val params: Params) : BaseDialogFragment() {

    private lateinit var binding: CustomDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        updateWindowFeature()
        binding = CustomDialogBinding.inflate(inflater, container, false)
        return binding.root.apply { setupDialog() }
    }

    private fun setupDialog() {
        binding.apply {
            customDialogMessageTextView.putText(params.message)

            customDialogActionButton.putText(params.actionMessage)
            customDialogActionButton.setOnClickListener { params.action() }
        }
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        params.dismiss.invoke()
    }

    data class Params(
        var message: String,
        var actionMessage: String = "OK",
        var action: () -> Unit = {},
        var dismiss: () -> Unit = {}
    )
}