package com.example.ssf.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.example.ssf.R
import kotlinx.android.synthetic.main.activity_detail_page.*


class DetailPageActivity : AppCompatActivity() {
    val TAG: String = "로그"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page)
        Log.d(TAG, "DetailPageActivity - onCreate() called")

        tv_title.text = intent.getStringExtra("title")
        tv_dday.text = intent.getStringExtra("dday")
        tv_heart.text = intent.getStringExtra("heart")
        tv_tag.text = intent.getStringExtra("tags")



        wv_detail.settings.javaScriptEnabled = true       // 자바 스크립트 허용

        /* 웹뷰에서 새 창이 뜨지 않도록 하는 구문 */
        wv_detail.webViewClient = WebViewClient()
        wv_detail.webChromeClient = WebChromeClient()
        /* 웹뷰에서 새 창이 뜨지 않도록 하는 구문 */

        wv_detail.loadUrl("https://www.google.com")       // 링크 주소를 로드함


        btn_comment.setOnClickListener{
            val intent2 = Intent(this, CommentActivity::class.java)
            startActivity(intent2)
        }

    }
}