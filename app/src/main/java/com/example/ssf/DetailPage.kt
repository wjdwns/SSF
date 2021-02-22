package com.example.ssf

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_detail_page.*
import kotlinx.android.synthetic.main.activity_main.*

class DetailPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page)


        tv_title.text = intent.getStringExtra("title")
        tv_dday.text = intent.getStringExtra("dday")
        tv_heart.text = intent.getStringExtra("heart")
        tv_tag.text = intent.getStringExtra("tags")



        wv_detail.settings.javaScriptEnabled = true       // 자바 스크립트 허용

        /* 웹뷰에서 새 창이 뜨지 않도록 하는 구문 */
        wv_detail.webViewClient = WebViewClient()
        wv_detail.webChromeClient = WebChromeClient()
        /* 웹뷰에서 새 창이 뜨지 않도록 하는 구문 */

        wv_detail.loadUrl("https://www.naver.com")       // 링크 주소를 로드함

    }
}