package com.example.ssf

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class listAdapter (val items: Array<itemList>) : RecyclerView.Adapter<listAdapter.viewHolder> () {

    class viewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val dday = itemView.findViewById<TextView>(R.id.tv_dday)
        val heart = itemView.findViewById<TextView>(R.id.tv_heart)
        val tags = itemView.findViewById<TextView>(R.id.tv_tag)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_example, parent, false)
        return viewHolder(view).apply {

//            view.setOnClickListener {
//                val curPos : Int = adapterPosition
//                val itm: itemList = items.get(curPos)
//
//                val myIntent: Intent = Intent(view.context, DetailPage::class.java)
//            }

        }
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
            val intent = Intent(holder.itemView.context, DetailPage::class.java)
            intent.putExtra("title", items.get(position).title)
            intent.putExtra("dday", items.get(position).dday)
            intent.putExtra("heart", items.get(position).heart)
            intent.putExtra("tags", items.get(position).tags)

            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }

    }


}