package com.dexapp.feature_region.fragment.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.dexapp.base_feature.core.BaseFragment
import com.dexapp.base_feature.util.delegateproperties.navDirections
import com.dexapp.base_feature.util.enums.GenerationEnum
import com.dexapp.base_feature.util.extensions.*
import com.dexapp.base_presentation.model.region.RegionInfoBinding
import com.dexapp.feature_region.adapter.RegionInfoViewPagerAdapter
import com.dexapp.feature_region.databinding.FragmentRegionInfoBinding
import com.dexapp.feature_region.navigation.info.RegionInfoNavigation
import com.dexapp.presentation_region.RegionInfoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegionInfoFragment : BaseFragment() {

    private val viewModel: RegionInfoViewModel by viewModel()
    private val navigation: RegionInfoNavigation by navDirections()

    private lateinit var binding: FragmentRegionInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        changeStatusBarColor(getColor())
        binding = FragmentRegionInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupView() {
        binding.run {
            regionInfoAppBarLayout.setBackgroundColor(getColor())
            regionInfoCollapsingToolbarLayout.setColorFilter(getColor())
            navigationIconImageView.setOnClickListener { navigation.navigateToRegion() }
        }

        viewModel.getRegionInfo(navigation.region.name)
    }

    private fun getColor() = requireContext().getCardViewColor(navigation.region.name)

    override fun addObservers(owner: LifecycleOwner) {
        viewModel.fetchRegionInfoViewState.onPostValue(owner) {
            setupInfo(it)
        }
    }

    private fun setupInfo(regionInfo: RegionInfoBinding) {
        binding.run {
            regionInfoNameTextView.putText(navigation.region.name.uppercase())
            regionInfoGenerationTextView.putText(GenerationEnum.getName(requireContext(), regionInfo.mainGeneration))
            regionInfoGenerationTextView.setVisible()

            regionInfoViewPager.adapter = RegionInfoViewPagerAdapter(
                requireActivity().supportFragmentManager,
                requireContext(),
                regionInfo
            )
            regionInfoTabLayout.setupWithViewPager(regionInfoViewPager)
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.cleanValues()
    }
}
