package com.chaseolson.pets.home.presenter

import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AlertDialog
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chaseolson.pets.home.model.PetListItemViewModel
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.fragment_home_screen.view.*


class HomeScreenPresenter {

    class Container(val root: View, private val listener: Listener) {
        val swipeLayout = root.home_swipelayout
        val progressBar = root.home_progressBar
        val scrollToTopButton = root.scroll_to_top_button
        val appBarLayout = root.pet_appbar_layout
        val petAdapter = PetRecyclerViewAdapter()
        private val petRecycler = root.pet_recyclerView

        init {
            swipeLayout.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    swipeLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)

                    val appBarHeight = appBarLayout.height
                    swipeLayout.translationY = swipeLayout.translationY - appBarHeight
                    swipeLayout.layoutParams.height = swipeLayout.height + appBarHeight
                }
            })

            swipeLayout.setOnRefreshListener { listener.swipeRefresh() }
            scrollToTopButton.setOnClickListener {
                petRecycler.smoothScrollToPosition(0)
            }
            petRecycler.layoutManager = GridLayoutManager(root.context, 2)
            petRecycler.adapter = petAdapter
            petRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = petRecycler.layoutManager as GridLayoutManager
                    if (layoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
                        val params = scrollToTopButton.layoutParams as CoordinatorLayout.LayoutParams
                        val behavior = params.behavior as HideBottomViewOnScrollBehavior<MaterialButton>
                        behavior.slideDown(scrollToTopButton)

                        appBarLayout.setExpanded(true)
                    }
                }
            })

            val topMarginDecoration = object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                    super.getItemOffsets(outRect, view, parent, state)
                    val position = parent.getChildAdapterPosition(view)
                    if (position == 0 || position == 1) {
                        outRect.set(0, 180, 0, 0)
                    } else {
                        outRect.set(0, 0, 0, 0)
                    }
                }
            }
            petRecycler.addItemDecoration(topMarginDecoration)
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