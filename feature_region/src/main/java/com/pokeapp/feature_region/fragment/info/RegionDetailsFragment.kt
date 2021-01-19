package com.pokeapp.feature_region.fragment.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pokeapp.base_feature.core.BaseFragment
import com.pokeapp.base_presentation.model.RegionBinding
import com.pokeapp.feature_region.R


class RegionDetailsFragment : BaseFragment() {

    private lateinit var mRegion: RegionBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_region_details, container, false)
    }

   /* @SuppressLint("DefaultLocale")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mRegion = checkNotNull(arguments?.getSerializable("region") as RegionBinding)

        val color = requireContext().getCardViewColor(mRegion.name)
        pokemonDetailsAppBarLayout.setBackgroundColor(color)
        pokemonDetailsCollapsingToolbarLayout.contentScrim?.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        activity?.window?.statusBarColor = color

        regionDetailsNameTextView.putText(mRegion.name.capitalize())
        regionDetailsGenerationTextView.putText(mRegion.mainGeneration.formatGenerationName())

        regionDetailsViewPager.adapter = RegionDetailsViewPagerAdapter(requireActivity().supportFragmentManager, requireContext(), mRegion)
        regionDetailsTabLayout.setupWithViewPager(regionDetailsViewPager)
    }

    override fun onStart() {
        super.onStart()

        // BACK BUTTON
        navigationIconImageView.setOnClickListener { findNavController().navigateUp() }
    }*/
}
