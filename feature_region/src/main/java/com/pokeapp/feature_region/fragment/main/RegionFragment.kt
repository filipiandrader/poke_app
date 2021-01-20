package com.pokeapp.feature_region.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import com.pokeapp.base_feature.core.BaseFragment
import com.pokeapp.base_feature.util.delegateproperties.navDirections
import com.pokeapp.base_feature.util.extensions.configureNoScroll
import com.pokeapp.base_feature.util.extensions.configureScroll
import com.pokeapp.base_feature.util.extensions.convertColor
import com.pokeapp.base_feature.util.extensions.enableScroll
import com.pokeapp.base_presentation.model.RegionBinding
import com.pokeapp.feature_region.R
import com.pokeapp.feature_region.adapter.RegionAdapter
import com.pokeapp.feature_region.databinding.FragmentRegionBinding
import com.pokeapp.feature_region.navigation.main.RegionNavigation
import com.pokeapp.presentation_region.RegionViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegionFragment : BaseFragment() {

    private val viewModel: RegionViewModel by viewModel()
    private val navigation: RegionNavigation by navDirections()

    private lateinit var regionAdapter: RegionAdapter
    private lateinit var binding: FragmentRegionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupView() {
        activity?.window?.statusBarColor = requireContext().convertColor(R.color.background)
        viewModel.getRegion()
        binding.navigationIconImageView.setOnClickListener { navigation.navigateToHome() }
    }

    override fun addObservers(owner: LifecycleOwner) {
        viewModel.fetchRegionViewState.onPostValue(owner) {
            setupRegion(it)
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
