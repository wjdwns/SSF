package com.example.ssf.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.example.ssf.R
import com.example.ssf.fragment.cart_fragment
import com.example.ssf.fragment.home_fragment
import com.example.ssf.fragment.search_setting_fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    val TAG: String = "로그"
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.d(TAG, "MainActivity - onNavigationItemSelected() called")
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
            R.id.main_cart_tab -> {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_main,cart_fragment())
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
        Log.d(TAG, "MainActivity - onCreate() called")

    }


}