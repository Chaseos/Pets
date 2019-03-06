package com.chaseolson.pets.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.chaseolson.pets.R
import kotlinx.android.synthetic.main.home_search_dialogfrag.view.*

class SearchDialog : DialogFragment() {

    companion object {
        val TAG = "SearchDialogTag"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_search_dialogfrag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ArrayAdapter.createFromResource(context, R.array.animal_type, android.R.layout.simple_spinner_item)
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    view.animal_type_spinner?.adapter = adapter
                }

        ArrayAdapter.createFromResource(context, R.array.animal_size, android.R.layout.simple_spinner_item)
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    view.size_spinner?.adapter = adapter
                }

        ArrayAdapter.createFromResource(context, R.array.animal_age, android.R.layout.simple_spinner_item)
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    view.age_spinner?.adapter = adapter
                }

        ArrayAdapter.createFromResource(context, R.array.animal_gender, android.R.layout.simple_spinner_item)
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    view.gender_spinner?.adapter = adapter
                }

        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        view.zip_code.requestFocus()
    }
}