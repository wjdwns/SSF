package com.example.ssf.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ssf.R
import com.example.ssf.retrofit2.TabService
import com.example.ssf.retrofit2.Tab_Data
import com.example.ssf.retrofit2.Tab_Output
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_detail_page.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DetailPageActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    val TAG: String = "로그"
    var ALL_idx:Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page)
        Log.d(TAG, "DetailPageActivity - onCreate() called")
        ALL_idx = intent.getIntExtra("ALL_idx", 0)
        tv_title.text = intent.getStringExtra("title")
        tv_host.text = intent.getStringExtra("host")
        val url = intent.getStringExtra("href")
        val usernum = intent.getIntExtra("유저넘버", 0)
        Log.d(TAG, "DetailPageActivity - onCreate() called $usernum")
        wv_detail.settings.javaScriptEnabled = true       // 자바 스크립트 허용

        /* 웹뷰에서 새 창이 뜨지 않도록 하는 구문 */
        wv_detail.webViewClient = WebViewClient()
        wv_detail.webChromeClient = WebChromeClient()
        /* 웹뷰에서 새 창이 뜨지 않도록 하는 구문 */

        wv_detail.loadUrl("$url")       // 링크 주소를 로드함
        nav_view1.setOnNavigationItemSelectedListener(this)

        btn_comment.setOnClickListener{
            val intent2 = Intent(this, CommentActivity::class.java)
            intent2.putExtra("유저넘버",usernum)
            intent2.putExtra("ALL_idx",ALL_idx)
            if(intent.getIntExtra("유저넘버",0)==0)
            {
                Toast.makeText(this,"비회원은 지원하지 않는 기능입니다.",Toast.LENGTH_SHORT)
            }
            else{
                startActivity(intent2)
            }
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.d(TAG, "DetailPageActivity - onNavigationItemSelected() called")
        val usernum = intent.getIntExtra("유저넘버", 0)
        val retrofit = Server_respond()
        val Tab = Tab_Data(usernum, ALL_idx!!)
        val TabService = retrofit.create(TabService::class.java)
        when(item.itemId){
            R.id.cart_tab -> {
                if(intent.getIntExtra("유저넘버",0)==0)
                {
                    Toast.makeText(this,"비회원은 지원하지 않는 기능입니다.",Toast.LENGTH_SHORT)
                }
                else {
                    TabService.requestTab(Tab).enqueue(object : Callback<Tab_Output> {
                        override fun onResponse(
                            call: Call<Tab_Output>,
                            response: Response<Tab_Output>
                        ) {
                            val res = response.body()
                            if (res?.Check == true) {
                                Toast.makeText(this@DetailPageActivity, "찜 성공.", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }

                        override fun onFailure(call: Call<Tab_Output>, t: Throwable) {
                            Toast.makeText(this@DetailPageActivity, "연결 실패.", Toast.LENGTH_SHORT)
                                .show()
                        }

                    })
                    return true
                }
            }
            R.id.home -> {
                return true

            }
            R.id.share -> {
                val sharingIntent = Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, intent.getStringExtra("href"));
                startActivity(Intent.createChooser(sharingIntent,"친구에게 공유하기"))
                return true

            }
        }
        return true
    }
    fun Server_respond(): Retrofit{
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build();
        var retrofit = Retrofit.Builder()
            .baseUrl("http://221.155.173.160:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit
    }
}