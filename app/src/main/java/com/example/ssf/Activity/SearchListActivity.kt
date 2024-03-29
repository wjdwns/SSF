package com.example.ssf.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ssf.Adapter.SearchlistAdapter
import com.example.ssf.List.ItemList
import com.example.ssf.R
import com.example.ssf.retrofit2.*
import kotlinx.android.synthetic.main.activity_detail_page.*
import kotlinx.android.synthetic.main.activity_keyword_search.*
import kotlinx.android.synthetic.main.activity_searchlist.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class SearchListActivity : AppCompatActivity() {
    var getintent: String? = null
    var user: Int? = null
    var page: Int? = null
    val TAG: String = "로그"
    var job :String? =null
    var ground :String? =null
    var condition :String? =null
    var age :Int? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchlist)
        val TAG: String = "로그"
        Log.d(TAG, "SearchListActivity - onCreate() called")
        Log.d(TAG, "MainActivity - onCreate() called $user")
        Toast.makeText(this, getintent, Toast.LENGTH_SHORT).show()
        getintent = intent.getStringExtra("검색어")
        user = intent.getIntExtra("유저넘버", 0)
        page = intent.getIntExtra("페이지", 0)
        job = intent.getStringExtra("job")
        ground = intent.getStringExtra("ground")
        condition = intent.getStringExtra("condition")
        age = intent.getIntExtra("age",0)
        if (page == 1) {
            PopularSearch()
        } else if (page == 2) {
            NewSearch()
        } else if (page == 3) {
            keywordSearch()

        } else if(page ==4){
            search_setting()
        } else{
            Log.d(TAG, "오류")
        }
    }


    fun keywordSearch() {
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
        var realItems = ArrayList<ItemList>()
        searchService.requestKeyword_Search(keyword).enqueue(object : Callback<List<grant_form>> {
            override fun onResponse(
                call: Call<List<grant_form>>,
                response: Response<List<grant_form>>
            ) {
                Toast.makeText(this@SearchListActivity, "연결 성공.", Toast.LENGTH_SHORT).show()
                val res = response.body()
                println(res?.size)
                for (i in 1..res?.size!!) {
                    realItems.add(
                        ItemList(
                            res?.get(i - 1).title,
                            res?.get(i - 1).host,
                            res?.get(i - 1).href,
                            user!!,
                            res?.get(i - 1).all_idx
                        )
                    )
                }

            }

            override fun onFailure(call: Call<List<grant_form>>, t: Throwable) {
                Toast.makeText(this@SearchListActivity, "연결 실패.", Toast.LENGTH_SHORT).show()
            }

        })
        val itemlist = findViewById<RecyclerView>(R.id.rv_itemlist)
        itemlist.layoutManager = LinearLayoutManager(this@SearchListActivity)
        itemlist.adapter = SearchlistAdapter(realItems)
        val decoDivider = DividerItemDecoration(rv_itemlist.context, 1)
        rv_itemlist.addItemDecoration(decoDivider)
        rv_itemlist.setHasFixedSize(true)
    }


    fun PopularSearch() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build();
        var retrofit = Retrofit.Builder()
            .baseUrl("http://221.155.173.160:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        val PopularService = retrofit.create(PoPular_Service::class.java)
        PopularService.requestPoPular().enqueue(object : Callback<List<grant_form>> {
            override fun onResponse(
                call: Call<List<grant_form>>,
                response: Response<List<grant_form>>
            ) {
                Toast.makeText(this@SearchListActivity, "연결성공", Toast.LENGTH_SHORT).show()
                val res = response.body()
                var realItems = ArrayList<ItemList>()

                for (i in 1..res?.size!!) {
                    realItems.add(
                        ItemList(
                            res?.get(i - 1).title,
                            res?.get(i - 1).host,
                            res?.get(i - 1).href,
                            user!!,
                            res?.get(i - 1).all_idx
                        )
                    )
                }
                val itemlist = findViewById<RecyclerView>(R.id.rv_itemlist)
                itemlist.layoutManager = LinearLayoutManager(this@SearchListActivity)
                itemlist.adapter = SearchlistAdapter(realItems)
                val decoDivider = DividerItemDecoration(rv_itemlist.context, 1)
                rv_itemlist.addItemDecoration(decoDivider)
                rv_itemlist.setHasFixedSize(true)
            }

            override fun onFailure(call: Call<List<grant_form>>, t: Throwable) {
                Toast.makeText(this@SearchListActivity, "연결실패", Toast.LENGTH_SHORT).show()
            }

        })
    }


    fun NewSearch() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build();
        var retrofit = Retrofit.Builder()
            .baseUrl("http://221.155.173.160:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        val newService = retrofit.create(New_Service::class.java)
        newService.requestNew().enqueue(object : Callback<List<grant_form>> {
            override fun onResponse(
                call: Call<List<grant_form>>,
                response: Response<List<grant_form>>
            ) {
                Toast.makeText(this@SearchListActivity, "연결성공", Toast.LENGTH_SHORT).show()
                val res = response.body()
                var realItems = ArrayList<ItemList>()
                for (i in 1..res?.size!!) {
                    realItems.add(
                        ItemList(
                            res?.get(i - 1).title,
                            res?.get(i - 1).host,
                            res?.get(i - 1).href,
                            user!!,
                            res?.get(i - 1).all_idx
                        )
                    )
                }
                val itemlist = findViewById<RecyclerView>(R.id.rv_itemlist)
                itemlist.layoutManager = LinearLayoutManager(this@SearchListActivity)
                itemlist.adapter = SearchlistAdapter(realItems)
                val decoDivider = DividerItemDecoration(rv_itemlist.context, 1)
                rv_itemlist.addItemDecoration(decoDivider)
                rv_itemlist.setHasFixedSize(true)
            }

            override fun onFailure(call: Call<List<grant_form>>, t: Throwable) {
                Toast.makeText(this@SearchListActivity, "연결실패", Toast.LENGTH_SHORT).show()
            }

        })
    }
    fun search_setting(){
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build();
        var retrofit = Retrofit.Builder()
            .baseUrl("http://221.155.173.160:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        val Searchset =retrofit.create(SearchSetting::class.java)

        Log.d(TAG, "SearchListActivity - search_setting() called"+job + ground + condition +age)
        val data = SearchSetting_Data(age!!,condition!!,job!!,ground!!)
        Searchset.requestSelect(data).enqueue(object : Callback<List<grant_form>>{
            override fun onResponse(
                call: Call<List<grant_form>>,
                response: Response<List<grant_form>>
            ) {
                Toast.makeText(this@SearchListActivity, "연결성공", Toast.LENGTH_SHORT).show()
                val res = response.body()
                var realItems = ArrayList<ItemList>()
                for (i in 1..res?.size!!) {
                    realItems.add(
                        ItemList(
                            res?.get(i - 1).title,
                            res?.get(i - 1).host,
                            res?.get(i - 1).href,
                            user!!,
                            res?.get(i - 1).all_idx
                        )
                    )
                }
                val itemlist = findViewById<RecyclerView>(R.id.rv_itemlist)
                itemlist.layoutManager = LinearLayoutManager(this@SearchListActivity)
                itemlist.adapter = SearchlistAdapter(realItems)
                val decoDivider = DividerItemDecoration(rv_itemlist.context, 1)
                rv_itemlist.addItemDecoration(decoDivider)
                rv_itemlist.setHasFixedSize(true)
            }

            override fun onFailure(call: Call<List<grant_form>>, t: Throwable) {
                Toast.makeText(this@SearchListActivity, "연결실패", Toast.LENGTH_SHORT).show()

            }

        })
    }
}