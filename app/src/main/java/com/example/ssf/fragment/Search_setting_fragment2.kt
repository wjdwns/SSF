package com.example.ssf.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ssf.R

class search_setting_fragment2 :Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (inflater != null) {
            inflater.inflate(R.layout.search_setting_fragment2, container, false)
        } else {
            return super.onCreateView(inflater, container, savedInstanceState)
        }
    }
}