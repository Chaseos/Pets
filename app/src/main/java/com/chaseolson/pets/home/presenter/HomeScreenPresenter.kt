package com.chaseolson.pets.home.presenter

import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AlertDialog
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chaseolson.pets.R
import com.chaseolson.pets.home.model.PetListItemViewModel
import kotlinx.android.synthetic.main.fragment_home_screen.view.*


class HomeScreenPresenter {

    class Container(val root: View, private val listener: Listener) {
        val swipeLayout = root.home_swipelayout
        val progressBar = root.home_progressBar
        val scrollToTopButton = root.scroll_to_top_button
        val petAdapter = PetRecyclerViewAdapter()
        private val petRecycler = root.pet_recyclerView

        init {
            swipeLayout.setOnRefreshListener { listener.swipeRefresh() }
            scrollToTopButton.setOnClickListener {
                petRecycler.smoothScrollToPosition(0)
            }
            petRecycler.layoutManager = GridLayoutManager(root.context, 2)
            petRecycler.adapter = petAdapter
            petRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val aniFadeOut = AnimationUtils.loadAnimation(scrollToTopButton.context, R.anim.fade_out)
                    val aniFadeIn = AnimationUtils.loadAnimation(scrollToTopButton.context, R.anim.fade_in)
                    val layoutManager = petRecycler.layoutManager as GridLayoutManager
                    when {
                        (dy > 0 || layoutManager.findFirstCompletelyVisibleItemPosition() == 0) && scrollToTopButton.visibility == View.VISIBLE -> {
                            scrollToTopButton.startAnimation(aniFadeOut)
                            scrollToTopButton.visibility = View.GONE
                            scrollToTopButton.isClickable = false
                        }
                        dy < 0 && scrollToTopButton.visibility == View.GONE -> {
                            scrollToTopButton.startAnimation(aniFadeIn)
                            scrollToTopButton.visibility = View.VISIBLE
                            scrollToTopButton.isClickable = true
                        }
                    }
                }
            })
        }
    }

    interface Listener {
        fun swipeRefresh()
    }

    companion object {

        fun present(container: Container, pets: PagedList<PetListItemViewModel.Pet>) {
            container.swipeLayout.isRefreshing = false
            container.progressBar.visibility = View.GONE
            container.petAdapter.submitList(pets)
        }

        fun presentError(container: Container, error: String) {
            container.progressBar.visibility = View.GONE
            val dialog = AlertDialog.Builder(container.root.context)
                    .setTitle(error)
                    .setPositiveButton("Ok") { _, _ ->
                        //TODO
                    }
                    .create()
            dialog.show()
        }
    }
}