package com.example.ssf.Dialog

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import com.example.ssf.R

class CommentMenuDialog : AppCompatDialogFragment () {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.dialog_comment_menu, container, false)
        return view


        //return super.onCreateView(inflater, container, savedInstanceState)
    }
}