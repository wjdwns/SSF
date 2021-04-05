package com.example.ssf.Adapter

import android.icu.text.Transliterator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ssf.List.itemList
import com.example.ssf.R
import kotlinx.android.synthetic.main.fragment_cart_fragment.view.*

class CartListAdapter (val items: Array<String>)  : RecyclerView.Adapter<CartListAdapter.viewHolder> () {

    class viewHolder(itemView : View)  :RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.tv_cartlist)
        val menu = itemView.findViewById<ImageButton>(R.id.btn_yellowdot)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_cart, parent, false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.title.text = items.get(position)

    }

}