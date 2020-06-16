package com.chaseolson.pets.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.chaseolson.pets.R
import com.chaseolson.pets.network.endpoints.PetFinderEndpoints
import com.chaseolson.pets.utils.animalToSearchQuery
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SearchFragment : Fragment() {

    val repo: PetFinderEndpoints by inject()

    interface SearchListener {
        fun search(searchModel: SearchModel)
    }

    val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewModelScope.launch {
            val typesList = repo.getPetTypes()
            if (typesList.isSuccessful) {
                animal_type_spinner?.adapter = typesList.body()?.types?.run {
                    ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_dropdown_item_1line,
                        map { it.name }.toMutableList()
                    )
                }?.apply {
                    setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                }
            }
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.animal_size,
            android.R.layout.simple_spinner_item
        )
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                size_spinner?.adapter = adapter
            }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.animal_age,
            android.R.layout.simple_spinner_item
        )
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                age_spinner?.adapter = adapter
            }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.animal_gender,
            android.R.layout.simple_spinner_item
        )
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                gender_spinner?.adapter = adapter
            }

        animal_type_spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (parent?.selectedItemPosition != 0) {
                        viewModel.viewModelScope.launch {
                            val response = repo.getPetBreeds(
                                parent?.selectedItem?.toString()!!
                            )
                            if (response.isSuccessful) {
                                animal_breed_spinner?.adapter = response.body()?.breeds?.run {
                                    ArrayAdapter(
                                        requireContext(),
                                        android.R.layout.simple_dropdown_item_1line,
                                        map { it.name }.toMutableList()
                                    )
                                }?.apply {
                                    setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                }
                            }
//                                breeds_auto_complete?.threshold = 1
//                                breeds_auto_complete?.setAdapter(adapter)
                        }
                    }
                }
            }

        search_button.setOnClickListener {
//            searchListener.search(
//                SearchModel(
//                    animal = animal_type_spinner.selectedItem?.toString()?.animalToSearchQuery(),
//                    breed = breeds_auto_complete.text?.toString(),
//                    size = size_spinner.selectedItem?.toString()?.sizeToSearchQuery(),
//                    sex = gender_spinner?.selectedItem?.toString()?.genderToSearchQuery(),
//                    age = if (age_spinner.selectedItemPosition != 0) age_spinner?.selectedItem?.toString() else null,
//                    location = zip_code?.text?.toString()
//                )
//            )
        }
    }

}