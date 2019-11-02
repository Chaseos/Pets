package com.chaseolson.pets.newhome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.chaseolson.pets.R
import com.chaseolson.pets.core.MainActivityViewModel2
import com.chaseolson.pets.databinding.HomeScreenFragmentBinding
import kotlinx.android.synthetic.main.home_screen_fragment.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeScreenFragment2 : Fragment() {
    private val homeViewModel3: HomeScreenViewModel3 by viewModel()
    private val mainActivityViewModel: MainActivityViewModel2 by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<HomeScreenFragmentBinding>(inflater, R.layout.home_screen_fragment, container, false).apply {
            viewModel = homeViewModel3
            lifecycleOwner = this@HomeScreenFragment2
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val controller = PetsListEpoxyController(homeViewModel3)
        view.pet_recyclerView.adapter = controller.adapter
        view.pet_recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        homeViewModel3.animalsLiveData.observe(viewLifecycleOwner, Observer { controller.submitList(it) })
        homeViewModel3.scrollToTop.observe(viewLifecycleOwner, Observer { view.pet_recyclerView.smoothScrollToPosition(0) })
    }
}
