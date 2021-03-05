package com.example.ssf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_comment.*
import kotlinx.android.synthetic.main.activity_comment_menu.*
import kotlinx.android.synthetic.main.comment_list_example.*

class CommentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

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

        btn_commentMenu.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.activity_comment_menu, null)

            val list = arrayOf("삭제하기","신고하기")
            lv_actions.adapter= ArrayAdapter(this, android.R.layout.simple_list_item_1,list)


            builder.setView(dialogView)

            builder.setNegativeButton("취소"){dialogInterface, i->
                /* 취소일 때 아무 액션이 없으므로 빈칸 */
            }

            builder.show()


        }


    }
}