package com.pokeapp.ui.fragments.region_details

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pokeapp.R
import com.pokeapp.presentation.model.Region
import com.pokeapp.ui.fragments.RegionDetailsViewPagerAdapter
import com.pokeapp.base_feature.util.extensions.PokemonColorUtil
import com.pokeapp.base_feature.util.extensions.formatGenerationName
import com.pokeapp.base_feature.util.extensions.putText
import kotlinx.android.synthetic.main.fragment_region_details.*
import org.jetbrains.anko.backgroundColor

/**
 * A simple [Fragment] subclass.
 */
class RegionDetailsFragment : Fragment() {

    private lateinit var mRegion: Region

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_region_details, container, false)
    }

    @SuppressLint("DefaultLocale")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mRegion = checkNotNull(arguments?.getSerializable("region") as Region)

        val color = PokemonColorUtil(view.context).getCardViewColor(mRegion.name)
        pokemonDetailsAppBarLayout.backgroundColor = color
        pokemonDetailsCollapsingToolbarLayout.contentScrim?.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        activity?.window?.statusBarColor = color

        regionDetailsNameTextView.putText(mRegion.name.capitalize())
        regionDetailsGenerationTextView.putText(mRegion.main_generation.formatGenerationName())

        regionDetailsViewPager.adapter = RegionDetailsViewPagerAdapter(activity!!.supportFragmentManager, requireContext(), mRegion)
        regionDetailsTabLayout.setupWithViewPager(regionDetailsViewPager)
    }

    override fun onStart() {
        super.onStart()

        // BACK BUTTON
        navigationIconImageView.setOnClickListener { findNavController().navigateUp() }
    }
}
