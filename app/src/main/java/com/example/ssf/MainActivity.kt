package com.example.ssf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //메인이 깨끗.



        cardview_1.setOnClickListener {
            val intent = Intent(this, SearchList::class.java)
            startActivity(intent)
        }
        cardview_4.setOnClickListener {
            val intent = Intent(this, Search_SettingActivity::class.java)
            startActivity(intent)
        }

        
    }
}