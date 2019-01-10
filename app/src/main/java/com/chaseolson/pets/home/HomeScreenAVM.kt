package com.chaseolson.pets.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chaseolson.pets.home.model.PetListItemViewModel
import com.chaseolson.pets.home.retrofit.PetListingApiImpl
import kotlinx.coroutines.*

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

    fun present(): LiveData<PetListItemViewModel> = present
    fun presentError(): LiveData<String> = presentError

    /**
     * Actionables
     */
    fun setup() {
        CoroutineScope(Job() + Dispatchers.Default).launch {
            logic.setup()
        }
    }
}