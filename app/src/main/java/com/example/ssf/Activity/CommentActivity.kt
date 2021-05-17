package com.example.ssf.Activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ssf.Adapter.commentlistAdapter
import com.example.ssf.List.commentList
import com.example.ssf.R
import kotlinx.android.synthetic.main.activity_comment.*


class CommentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)
//.
        val commentLists = arrayOf(
            commentList("감스트", "오우오우 싸발적이고"),
            commentList("배연두", "이 지원금은 정말 유용했어요!"),
            commentList("이재명", "제가 10만원을 뿌렸답니다"),
            commentList("익명이", "주변에서 받은 사람 한 번도 못 봄")
        )

        rv_comment.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_comment.adapter = commentlistAdapter(commentLists)

        val decoDivider = DividerItemDecoration(rv_comment.context, 1)
        rv_comment.addItemDecoration(decoDivider)

        rv_comment.setHasFixedSize(true)


    }
}