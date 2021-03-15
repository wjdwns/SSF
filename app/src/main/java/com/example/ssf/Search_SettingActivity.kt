package com.example.ssf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_search__setting.*

class Search_SettingActivity : AppCompatActivity() {
    private val mFragmentManager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search__setting)
        mFragmentManager.beginTransaction().add(R.id.fragment, search_setting_fragment1()).commit()
        select1.setOnClickListener{
            val getTopFragment = supportFragmentManager.findFragmentById(R.id.fragment)
            val fragmentTransaction = mFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment,search_setting_fragment1()).commit()
            }
        select2.setOnClickListener {
            val getTopFragment = supportFragmentManager.findFragmentById(R.id.fragment)
            val fragmentTransaction = mFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment,search_setting_fragment2()).commit()
        }
    }
}
