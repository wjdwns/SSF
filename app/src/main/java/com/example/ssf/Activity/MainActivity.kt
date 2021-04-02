package com.example.ssf.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.ssf.R
import com.example.ssf.fragment.home_fragment
import com.example.ssf.fragment.search_setting_fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_search_setting_fragment.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.main_home ->{
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_main,home_fragment())
                transaction.commit()
                return true
            }
            R.id.search_button ->{
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_main,search_setting_fragment())
                transaction.commit()
                return true
            }
        }
        return false
    }
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mFragmentManager = supportFragmentManager
        mFragmentManager.beginTransaction().replace(R.id.fragment_main, home_fragment()).commit()
        nav_view.setOnNavigationItemSelectedListener(this)


    }


}