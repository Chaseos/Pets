package com.chaseolson.pets.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.chaseolson.pets.R
import com.chaseolson.pets.databinding.HomeScreenFragmentBinding
import com.chaseolson.pets.ui.main.MainActivityViewModel
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior
import org.koin.android.viewmodel.ext.android.viewModel

class HomeScreenFragment : Fragment() {
    private val homeViewModel: HomeScreenViewModel by viewModel()
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    private lateinit var binding: HomeScreenFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<HomeScreenFragmentBinding>(inflater, R.layout.home_screen_fragment, container, false).apply {
            binding = this
            viewModel = homeViewModel
            lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val controller = PetsListEpoxyController(homeViewModel)
        binding.petRecyclerView.adapter = controller.adapter
        binding.petRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.executePendingBindings()

        postponeEnterTransition()
        binding.petRecyclerView.viewTreeObserver
            .addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }

        homeViewModel.petsLiveData.observe(viewLifecycleOwner, Observer {
            controller.submitList(it)
            binding.homeSwipelayout.isRefreshing = false
        })

        homeViewModel.scrollToTop.observe(viewLifecycleOwner) {
            binding.petRecyclerView.smoothScrollToPosition(0)
            binding.scrollToTopButton.run {
                ((layoutParams as CoordinatorLayout.LayoutParams).behavior as HideBottomViewOnScrollBehavior<View>).slideDown(
                    this
                )
            }
        }

        homeViewModel.searchClicked.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_homeScreenFragment_to_searchFragment)
        }

        homeViewModel.petClickedAction.observe(viewLifecycleOwner) {
            mainActivityViewModel.selectedPet = it.petDetailViewState
            val extras = FragmentNavigatorExtras(
                it.petImage to it.petImage.transitionName,
                it.petName to it.petName.transitionName
            )
            findNavController().navigate(R.id.action_homeScreenFragment_to_petDetailFragment, null, null, extras)
        }
    }
}
