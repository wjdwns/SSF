package com.example.ssf

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class search_setting_fragment1 :Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (inflater != null) {
            inflater.inflate(R.layout.search_setting_fragment1, container, false)
        } else {
            return super.onCreateView(inflater, container, savedInstanceState)
        }
    }
}