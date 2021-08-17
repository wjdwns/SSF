package com.example.ssf.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ssf.Adapter.SearchlistAdapter
import com.example.ssf.List.itemList
import com.example.ssf.R
import com.example.ssf.retrofit2.IKeyword_search
import com.example.ssf.retrofit2.Keyword_Search
import com.example.ssf.retrofit2.grant_form
import kotlinx.android.synthetic.main.activity_detail_page.*
import kotlinx.android.synthetic.main.activity_keyword_search.*
import kotlinx.android.synthetic.main.activity_searchlist.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchlist)
        val TAG: String = "로그"
        Log.d(TAG, "SearchListActivity - onCreate() called")
        val getintent = intent.getStringExtra("검색어")
        val getintent2 = intent.getIntExtra("유저넘버",0)
        Log.d(TAG, "MainActivity - onCreate() called $getintent2")
        Toast.makeText(this,getintent,Toast.LENGTH_SHORT).show()
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build();
        var retrofit = Retrofit.Builder()
            .baseUrl("http://221.155.173.160:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        val keyword = Keyword_Search(getintent)
        println(keyword)
        val searchService = retrofit.create(IKeyword_search::class.java)
        var realItems = ArrayList<itemList>()
        searchService.requestKeyword_Search(keyword).enqueue(object : Callback<List<grant_form>> {
            override fun onResponse(
                call: Call<List<grant_form>>,
                response: Response<List<grant_form>>
            ) {
                Toast.makeText(this@SearchListActivity,"연결 성공.", Toast.LENGTH_SHORT).show()
                val res = response.body()
                println(res?.size)
                for(i in 1..res?.size!!){
                    realItems.add(itemList(res?.get(i-1).title,res?.get(i-1).host,res?.get(i-1).href,getintent2,res?.get(i-1).all_idx))
                }

            }

            override fun onFailure(call: Call<List<grant_form>>, t: Throwable) {
                Toast.makeText(this@SearchListActivity,"연결 실패.", Toast.LENGTH_SHORT).show()
            }

        })
        val itemlist = findViewById<RecyclerView>(R.id.rv_itemlist)
        itemlist.layoutManager = LinearLayoutManager(this@SearchListActivity)
        itemlist.adapter = SearchlistAdapter(realItems)

        val decoDivider = DividerItemDecoration(rv_itemlist.context, 1)
        rv_itemlist.addItemDecoration(decoDivider)

        rv_itemlist.setHasFixedSize(true)



    }
}