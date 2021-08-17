package com.example.ssf.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.example.ssf.R
import com.example.ssf.retrofit2.IKeyword_search
import com.example.ssf.retrofit2.Keyword_Search
import com.example.ssf.retrofit2.grant_form
import kotlinx.android.synthetic.main.activity_keyword_search.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class Keyword_searchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keyword_search)
        val TAG: String = "로그"
        val getintent = intent.getIntExtra("유저넘버",0)
        Log.d(TAG, "Keyword_searchActivity - onCreate() called $getintent")
        search_keyword.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val intent = Intent(this@Keyword_searchActivity,SearchListActivity::class.java)
                intent.putExtra("검색어", query)
                intent.putExtra("유저넘버", getintent)
                startActivity(intent)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }
}