package com.chaseolson.pets.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.chaseolson.pets.R
import com.chaseolson.pets.databinding.HomeScreenFragmentBinding
import com.chaseolson.pets.databinding.PetDetailFragmentBinding
import com.chaseolson.pets.main.MainActivityViewModel
import com.google.android.material.appbar.AppBarLayout

class PetDetailFragment : Fragment() {
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    val petDetailViewModel: PetDetailViewModel by viewModels()
    private lateinit var binding: PetDetailFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<PetDetailFragmentBinding>(inflater, R.layout.pet_detail_fragment, container, false).apply {
            binding = this
            lifecycleOwner = viewLifecycleOwner
            pet = mainActivityViewModel.selectedPet
            coordinateMotion()
        }.root
    }

    private fun coordinateMotion() {
        val appBarLayout: AppBarLayout = binding.appbarLayout
        val motionLayout: MotionLayout = binding.motionLayout

        val listener = AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            val seekPosition = -verticalOffset / appBarLayout.totalScrollRange.toFloat()
            motionLayout.progress = seekPosition
        }

        appBarLayout.addOnOffsetChangedListener(listener)

    }

}