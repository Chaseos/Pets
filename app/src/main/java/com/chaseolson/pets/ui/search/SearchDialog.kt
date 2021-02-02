package com.chaseolson.pets.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.chaseolson.pets.R

class SearchDialog : DialogFragment() {

    interface SearchDialogListener {
        fun search(searchModel: SearchModel)
    }

    companion object {
        val TAG = "SearchDialogTag"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        ArrayAdapter.createFromResource(context, R.array.animal_type, android.R.layout.simple_spinner_item)
//                .also { adapter ->
//                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                    view.animal_type_spinner?.adapter = adapter
//                }
//
//        ArrayAdapter.createFromResource(context, R.array.animal_size, android.R.layout.simple_spinner_item)
//                .also { adapter ->
//                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                    view.size_spinner?.adapter = adapter
//                }
//
//        ArrayAdapter.createFromResource(context, R.array.animal_age, android.R.layout.simple_spinner_item)
//                .also { adapter ->
//                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                    view.age_spinner?.adapter = adapter
//                }
//
//        ArrayAdapter.createFromResource(context, R.array.animal_gender, android.R.layout.simple_spinner_item)
//                .also { adapter ->
//                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                    view.gender_spinner?.adapter = adapter
//                }

        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
//        view.zip_code.requestFocus()

//        val callback = object : Callback<OldPetBreedsResponse> {
//            override fun onResponse(call: Call<OldPetBreedsResponse>, response: Response<OldPetBreedsResponse>) {
//                val breeds = response.body()?.breeds
//                val adapter = breeds?.run { ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, breeds.map { it.breed }.toMutableList()) }
//                view.breeds_auto_complete?.threshold = 1
//                view.breeds_auto_complete?.setAdapter(adapter)
//            }
//
//            override fun onFailure(call: Call<OldPetBreedsResponse>, t: Throwable) {}
//
//        }

//        view.animal_type_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(parent: AdapterView<*>?) {}
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                if (parent?.selectedItemPosition != 0) {
////                    RetrofitApi().getPetBreeds(parent?.selectedItem?.toString()?.animalToSearchQuery()).enqueue(callback)
//                }
//            }
//        }
//
//        view.search_button.setOnClickListener {
//            (targetFragment as SearchDialogListener).search(
//                SearchModel(
//                    animal = view.animal_type_spinner.selectedItem?.toString()?.animalToSearchQuery(),
//                    breed = view.breeds_auto_complete.text?.toString(),
//                    size = view.size_spinner.selectedItem?.toString()?.sizeToSearchQuery(),
//                    sex = view.gender_spinner?.selectedItem?.toString()?.genderToSearchQuery(),
//                    age = if (view.age_spinner.selectedItemPosition != 0) view.age_spinner?.selectedItem?.toString() else null,
//                    location = view.zip_code?.text?.toString()
//                )
//            )
//            dismiss()
//        }
    }
}