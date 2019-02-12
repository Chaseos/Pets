package com.chaseolson.pets.home.presenter

import android.app.Dialog
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chaseolson.pets.home.model.PetListItemViewModel
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.fragment_home_screen.view.*
import kotlinx.android.synthetic.main.location_dialog.*
import kotlinx.coroutines.*


class HomeScreenPresenter {

    class Container(val root: View, private val listener: Listener) {
        val swipeLayout: SwipeRefreshLayout = root.home_swipelayout
        val progressBar: ProgressBar = root.home_progressBar
        val scrollToTopButton: MaterialCardView = root.scroll_to_top_button
        val appBarLayout: AppBarLayout = root.pet_appbar_layout
        val location: TextView = root.location
        val petAdapter = PetRecyclerViewAdapter()
        val petRecycler: RecyclerView = root.pet_recyclerView

        init {
            swipeLayout.setOnRefreshListener { listener.swipeRefresh() }

            scrollToTopButton.setOnClickListener { petRecycler.smoothScrollToPosition(0) }

            petRecycler.layoutManager = GridLayoutManager(root.context, 2)
            petRecycler.adapter = petAdapter
            petRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = petRecycler.layoutManager as GridLayoutManager
                    if (layoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
                        val params = scrollToTopButton.layoutParams as CoordinatorLayout.LayoutParams
                        val behavior = params.behavior as HideBottomViewOnScrollBehavior<MaterialCardView>
                        behavior.slideDown(scrollToTopButton)

                        appBarLayout.setExpanded(true)
                    }

                    GlobalScope.launch(Dispatchers.Main) {
                        if (petRecycler.scrollState == RecyclerView.SCROLL_STATE_IDLE) {
                            repeat(4) {
                                delay(500)
                                if (petRecycler.scrollState != RecyclerView.SCROLL_STATE_IDLE) cancel()
                            }
                            if (petRecycler.scrollState == RecyclerView.SCROLL_STATE_IDLE) appBarLayout.setExpanded(true)
                        }
                    }
                }
            })

            location.setOnClickListener {
                val dialog = Dialog(it.context)
                dialog.setContentView(com.chaseolson.pets.R.layout.location_dialog)
                val layoutParams = dialog.window.attributes
                layoutParams.gravity = Gravity.TOP
                layoutParams.y = 79
                layoutParams.flags += WindowManager.LayoutParams.FLAG_DIM_BEHIND
                layoutParams.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE
                dialog.window.attributes = layoutParams
                val search = dialog.search_icon
                val zipCode = dialog.zip_code
                zipCode.isFocusableInTouchMode = true
                zipCode.requestFocus()
                search.setOnClickListener {
                    when {
                        zipCode.text.toString().isBlank() -> dialog.dismiss()
                        zipCode.text.toString().length != 5 -> dialog.zip_code_layout.error = "Must be 5 numbers"
                        else -> {
                            Toast.makeText(root.context, zipCode.text.toString(), Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                        }
                    }
                }
                zipCode.setOnEditorActionListener { v, actionId, event ->
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        when {
                            zipCode.text.toString().isBlank() -> dialog.dismiss()
                            zipCode.text.toString().length != 5 -> dialog.zip_code_layout.error = "Must be 5 numbers"
                            else -> {
                                Toast.makeText(root.context, zipCode.text.toString(), Toast.LENGTH_SHORT).show()
                                dialog.dismiss()
                            }
                        }
                    }
                    false
                }
                dialog.show()
            }
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