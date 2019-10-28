package com.chaseolson.pets.newhome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.chaseolson.pets.R
import com.chaseolson.pets.core.MainActivityViewModel2
import com.chaseolson.pets.databinding.HomeScreenFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class HomeScreenFragment : Fragment() {
    private val homeViewModel2: HomeScreenViewModel2 by viewModel()
    private val mainActivityViewModel: MainActivityViewModel2 by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<HomeScreenFragmentBinding>(inflater, R.layout.home_screen_fragment, container, false).apply {
            viewModel = homeViewModel2
            lifecycleOwner = this@HomeScreenFragment
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}
