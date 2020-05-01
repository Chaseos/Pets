package com.chaseolson.pets.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.chaseolson.pets.R
import com.chaseolson.pets.main.MainActivityViewModel
import com.chaseolson.pets.databinding.HomeScreenFragmentBinding
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior
import kotlinx.android.synthetic.main.home_screen_fragment.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeScreenFragment : Fragment() {
    private val homeViewModel: HomeScreenViewModel by viewModel()
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<HomeScreenFragmentBinding>(
            inflater,
            R.layout.home_screen_fragment,
            container,
            false
        ).apply {
            viewModel = homeViewModel
            lifecycleOwner = this@HomeScreenFragment
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val controller = PetsListEpoxyController(homeViewModel)
        view.pet_recyclerView.adapter = controller.adapter
        view.pet_recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        homeViewModel.animalsLiveData.observe(viewLifecycleOwner, Observer {
            controller.submitList(it)
//            view.home_swipelayout.isRefreshing = false
        })
        homeViewModel.scrollToTop.observe(viewLifecycleOwner, Observer {
            view.pet_recyclerView.smoothScrollToPosition(0)
            view.scroll_to_top_button.run {
                ((layoutParams as CoordinatorLayout.LayoutParams).behavior as HideBottomViewOnScrollBehavior<View>).slideDown(
                    this
                )
            }
        })
    }
}
