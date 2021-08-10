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
        val TAG: String = "로그"
        Log.d(TAG, "SearchListActivity - onCreate() called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchlist)
        val getintent = intent.getStringExtra("검색어")
        Toast.makeText(this,getintent,Toast.LENGTH_SHORT).show()
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build();
        var retrofit = Retrofit.Builder()
            .baseUrl("http://221.155.173.160:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        val search = Keyword_Search(getintent)
        val searchService = retrofit.create(IKeyword_search::class.java)
        searchService.requestKeyword_Search(search).enqueue(object : Callback<List<grant_form>> {
            override fun onResponse(
                call: Call<List<grant_form>>,
                response: Response<List<grant_form>>
            ) {
                Toast.makeText(this@SearchListActivity,"연결 성공.", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<List<grant_form>>, t: Throwable) {
                Toast.makeText(this@SearchListActivity,"연결 실패.", Toast.LENGTH_SHORT).show()
            }

        })
        val realItems = arrayOf(
            itemList("국가장학금", "D-10", "♥ 56", "#장학금 #국가장학금"),
            itemList("숭실대학교 백마우수어쩌구", "D-153", "♥ 90", "#장학금 #교내장학금"),
            itemList("경기도 2차 재난지원금", "D-DAY", "♥ 27", "#재난지원금 #경기도 #코로나"),
            itemList("청년창업지원금", "D-43", "♥ 8", "#지원금 #20대 #창업"),
            itemList("아이디어고갈", "D-DAY", "♥ 3476", "#배연두 #화이팅 #싸발적"),
            itemList("청년창업지원금", "D-43", "♥ 8", "#지원금 #20대 #창업"),
            itemList("한국대학사회봉사협의회 WFK 청년어쩌궁", "D-9", "♥ 19", "#봉사활동 #대학생"),
            itemList("안드로이드 스튜디오", "D-1", "♥ 1892", "#앱개발 #공부 #현타"),
            itemList("예시만들어보는중 글이 길어지면 말줄임표가 보이게 해놨습니당^^", "D-34", "♥ 76", "#예시"),
            itemList("청년창업지원금", "D-43", "♥ 8", "#지원금 #20대 #창업"),
            itemList("청년창업지원금", "D-43", "♥ 8", "#지원금 #20대 #창업"),
            itemList("청년창업지원금", "D-43", "♥ 8", "#지원금 #20대 #창업")

        )
        val itemlist = findViewById<RecyclerView>(R.id.rv_itemlist)
        itemlist.layoutManager = LinearLayoutManager(this)
        itemlist.adapter = SearchlistAdapter(realItems)

        val decoDivider = DividerItemDecoration(rv_itemlist.context, 1)
        rv_itemlist.addItemDecoration(decoDivider)

        rv_itemlist.setHasFixedSize(true)

    }
}