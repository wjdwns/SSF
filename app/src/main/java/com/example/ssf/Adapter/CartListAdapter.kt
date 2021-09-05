package com.example.ssf.Adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.ssf.Activity.CommentActivity
import com.example.ssf.Activity.DetailPageActivity
import com.example.ssf.Activity.MainActivity
import com.example.ssf.Dialog.CommentMenuDialog
import com.example.ssf.List.ItemList
import com.example.ssf.R
import com.example.ssf.fragment.cart_fragment
import com.example.ssf.retrofit2.Cart_Del
import com.example.ssf.retrofit2.Cart_Del_Data
import com.example.ssf.retrofit2.Cart_Del_Output
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class CartListAdapter (val items: ArrayList<ItemList>)  : RecyclerView.Adapter<CartListAdapter.viewHolder> () {

    class viewHolder(itemView : View)  :RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.tv_cartlist)
        val Menu = itemView.findViewById<ImageButton>(R.id.btn_cartMenu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_cart, parent, false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val TAG: String = "로그"
        val user = items.get(position).usernum
        val ALL_idx = items.get(position).ALL_idx
        holder.title.text = items.get(position).title
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,DetailPageActivity::class.java)
            Log.d(TAG, "CartListAdapter - onBindViewHolder() called" + items.get(position).title)
            intent.putExtra("href",items.get(position).href)
            intent.putExtra("title", items.get(position).title)
            intent.putExtra("host", items.get(position).host)
            intent.putExtra("유저넘버",items.get(position).usernum)
            intent.putExtra("ALL_idx",items.get(position).ALL_idx)

            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
        holder.Menu.setOnClickListener{
            val fragmentdialog = CommentMenuDialog()
            fragmentdialog.setOnClickListener(object : CommentMenuDialog.OnButtonClickListener{
                override fun onButton1Clicked() {
                    val retrofit = Server_respond()
                    val delcart = retrofit.create(Cart_Del::class.java)
                    val Cartdata = Cart_Del_Data(user,ALL_idx)
                    delcart.requestCartdel(Cartdata).enqueue(object : Callback<Cart_Del_Output>{
                        override fun onResponse(
                            call: Call<Cart_Del_Output>,
                            response: Response<Cart_Del_Output>
                        ) {
                            val res = response.body()
                            if(res?.Check == true){
                                Toast.makeText(it.context,"찜하기 취소하였습니다.", Toast.LENGTH_SHORT).show()
                            }
                            else{
                                Toast.makeText(it.context, "오류.", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<Cart_Del_Output>, t: Throwable) {
                            Toast.makeText(it.context, "연결 실패.", Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            })
            fragmentdialog.show((it.context as MainActivity).supportFragmentManager, "By.김한슬")
        }

    }
    fun Server_respond(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build();
        var retrofit = Retrofit.Builder()
            .baseUrl("http://221.155.173.160:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit
    }

}