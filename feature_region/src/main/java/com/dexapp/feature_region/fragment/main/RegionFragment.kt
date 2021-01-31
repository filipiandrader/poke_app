package com.dexapp.feature_region.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import com.dexapp.base_feature.core.BaseFragment
import com.dexapp.base_feature.customview.dialog.MessageDialog
import com.dexapp.base_feature.util.delegateproperties.navDirections
import com.dexapp.base_feature.util.extensions.*
import com.dexapp.base_presentation.model.region.RegionBinding
import com.dexapp.domain.exception.ServerException
import com.dexapp.feature_region.R
import com.dexapp.feature_region.adapter.RegionAdapter
import com.dexapp.feature_region.databinding.FragmentRegionBinding
import com.dexapp.feature_region.navigation.main.RegionNavigation
import com.dexapp.presentation_region.RegionViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegionFragment : BaseFragment() {

    private val viewModel: RegionViewModel by viewModel()
    private val navigation: RegionNavigation by navDirections()

    private lateinit var regionAdapter: RegionAdapter
    private lateinit var binding: FragmentRegionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        changeStatusBarColor(getColor())
        binding = FragmentRegionBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun getColor() = requireContext().convertColor(R.color.background)

    override fun setupView() {
        viewModel.getRegion()
        binding.navigationIconImageView.setOnClickListener { navigation.navigateToHome() }
    }

    override fun addObservers(owner: LifecycleOwner) {
        viewModel.fetchRegionViewState.onPostValue(owner,
            onSuccess = {
                setupRegion(it)
            },
            onError = {
                when (it) {
                    is ServerException -> handleDialogServerException(it)
                    else -> handleGenericDialog(it)
                }
            }
        )
    }

    private fun handleDialogServerException(exception: ServerException) {
        exception.message?.let {
            showDialog(
                MessageDialog.Params(
                    message = it,
                    action = { viewModel.getRegion() }
                )
            )
        }
    }

    private fun handleGenericDialog(throwable: Throwable) {
        throwable.message?.let {
            showDialog(MessageDialog.Params(message = it))
        }
    }

    private fun setupRegion(regions: List<RegionBinding>) {
        binding.run {
            regionAdapter = RegionAdapter { navigation.navigateToInfo(it) }
            regionAdapter.items = regions.toMutableList()
            regionRecyclerView.apply {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = regionAdapter
                regionRecyclerView.enableScroll(regions.size > 12)
            }

            if (regions.size > 12) {
                regionCollapsingToolbarLayout.configureNoScroll()
            } else {
                regionCollapsingToolbarLayout.configureScroll()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.cleanValues()
    }
}
