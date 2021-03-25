package com.example.ssf.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ssf.CommentActivity
import com.example.ssf.CommentMenuDialog
import com.example.ssf.R
import com.example.ssf.commentList

class commentlistAdapter(val comments: Array<commentList>) : RecyclerView.Adapter<commentlistAdapter.viewholder> () {

    class viewholder (val commentView: View) :RecyclerView.ViewHolder(commentView)  {

        val name = commentView.findViewById<TextView>(R.id.tv_name)
        val comment = commentView.findViewById<TextView>(R.id.tv_comment)
        val commentMenu = commentView.findViewById<ImageButton>(R.id.btn_commentMenu)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_list_example, parent, false)
        return viewholder(view)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.name.text = comments.get(position).name
        holder.comment.text = comments.get(position).comment
        holder.commentMenu.setOnClickListener {
            CommentMenuDialog().show((it.context as CommentActivity).supportFragmentManager, "By.김한슬")
        }
    }
}