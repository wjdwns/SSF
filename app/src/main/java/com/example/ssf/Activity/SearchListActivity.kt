package com.example.ssf.List

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ssf.Adapter.SearchlistAdapter
import com.example.ssf.R
import kotlinx.android.synthetic.main.activity_detail_page.*
import kotlinx.android.synthetic.main.activity_searchlist.*

class SearchListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchlist)

        tv_listTitle.text = "검색 결과"

        val realItems = arrayOf(
            itemList("국가장학금", "D-10", "♥ 56", "#장학금 #국가장학금"),
            itemList("숭실대학교 백마우수어쩌구", "D-153", "♥ 90", "#장학금 #교내장학금"),
            itemList("경기도 2차 재난지원금", "D-DAY", "♥ 27", "#재난지원금 #경기도 #코로나"),
            itemList("청년창업지원금", "D-43", "♥ 8", "#지원금 #20대 #창업"),
            itemList("아이디어고갈", "D-DAY", "♥ 3476", "#배연두 #화이팅 #싸발적"),
            itemList("청년창업지원금", "D-43", "♥ 8", "#지원금 #20대 #창업"),
            itemList("한국대학사회봉사협의회 WFK 청년어쩌궁", "D-9", "♥ 19", "#봉사활동 #대학생"),
            itemList("안드로이드 스튜디오", "D-1", "♥ 1892", "#앱개발 #공부 #현타"),
            itemList("예시만들어보는중 글이 길어지면 말줄임표가 보이게 해놨습니당^^", "D-34", "♥ 76", "#예시 #태그도길게해보는중 #어디까지 #더길게더길게더길게 #더더더더더더더더더더더더더더ㅓ"),
            itemList("청년창업지원금", "D-43", "♥ 8", "#지원금 #20대 #창업"),
            itemList("청년창업지원금", "D-43", "♥ 8", "#지원금 #20대 #창업"),
            itemList("청년창업지원금", "D-43", "♥ 8", "#지원금 #20대 #창업")

        )

        rv_itemlist.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_itemlist.adapter = SearchlistAdapter(realItems)

        val decoDivider = DividerItemDecoration(rv_itemlist.context, 1)
        rv_itemlist.addItemDecoration(decoDivider)

        rv_itemlist.setHasFixedSize(true)

    }
}