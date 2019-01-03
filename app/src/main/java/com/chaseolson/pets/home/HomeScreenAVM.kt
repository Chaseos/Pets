package com.chaseolson.pets.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chaseolson.pets.home.model.PetItemViewModel

class HomeScreenAVM : ViewModel() {
    private val logic: HomeScreenLogic
    private val present = MutableLiveData<PetItemViewModel>()
    private val presentError = MutableLiveData<String>()

    init {
        val listener = object : HomeScreenLogic.Listener {

            override fun present(vm: PetItemViewModel?) {
                present.postValue(vm)
            }

            override fun presentError(error: String) {
                presentError.postValue(error)
            }
        }

        logic = HomeScreenLogic(listener)
    }

    fun present(): LiveData<PetItemViewModel> = present
    fun presentError(): LiveData<String> = presentError

    /**
     * Actionables
     */
    fun setup() {
        logic.setup()
    }
}