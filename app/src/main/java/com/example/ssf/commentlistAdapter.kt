package com.example.ssf

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.Array.newInstance

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