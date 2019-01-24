package com.chaseolson.pets.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chaseolson.pets.home.model.PetListItemViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeScreenAVM : ViewModel() {
    private val logic: HomeScreenLogic
    private val present = MutableLiveData<PetListItemViewModel>()
    private val presentError = MutableLiveData<String>()

    init {
        val listener = object : HomeScreenLogic.Listener {

            override fun present(vm: PetListItemViewModel?) {
                present.postValue(vm)
            }

            override fun presentError(error: String) {
                presentError.postValue(error)
            }
        }

        logic = HomeScreenLogic(listener, PetListingApiImpl())
    }

    /**
     * Observables
     */

    fun present(): LiveData<PetListItemViewModel> = present

    fun presentError(): LiveData<String> = presentError

    /**
     * Actionables
     */
    fun setup() {
        CoroutineScope(Job() + Dispatchers.Default).launch {
            logic.start()
        }
    }
}