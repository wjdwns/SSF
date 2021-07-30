package com.example.ssf.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.ssf.Activity.DetailPageActivity
import com.example.ssf.R
import com.example.ssf.List.itemList

class SearchlistAdapter (val items: Array<itemList>) : RecyclerView.Adapter<SearchlistAdapter.viewHolder> () {

    class viewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.grant_title)
        val dday = itemView.findViewById<TextView>(R.id.grant_dday)
        val heart = itemView.findViewById<TextView>(R.id.grant_heart)
        val tags = itemView.findViewById<TextView>(R.id.grant_tag)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_search, parent, false)
        return viewHolder(view)
        }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.title.text = items.get(position).title
        holder.dday.text = items.get(position).dday
        holder.heart.text = items.get(position).heart
        holder.tags.text = items.get(position).tags


        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailPageActivity::class.java)
            intent.putExtra("title", items.get(position).title)
            intent.putExtra("dday", items.get(position).dday)
            intent.putExtra("heart", items.get(position).heart)
            intent.putExtra("tags", items.get(position).tags)

            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }

    }


}