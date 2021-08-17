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

class SearchlistAdapter(val items: ArrayList<itemList>) : RecyclerView.Adapter<SearchlistAdapter.viewHolder> () {

    class viewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.grant_title)
        val heart = itemView.findViewById<TextView>(R.id.grant_heart)

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
        holder.heart.text = items.get(position).heart



        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailPageActivity::class.java)
            intent.putExtra("href",items.get(position).href)
            intent.putExtra("title", items.get(position).title)
            intent.putExtra("heart", items.get(position).heart)



            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }

    }


}