package com.chaseolson.pets.core

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.chaseolson.pets.R

class ZipCodeDialogFrag : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)

            builder.setView(layoutInflater.inflate(R.layout.location_dialog, null))
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}