package com.example.ssf.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.ssf.Activity.CommentActivity
import com.example.ssf.Dialog.CommentMenuDialog
import com.example.ssf.R
import com.example.ssf.List.CommentList
import com.example.ssf.retrofit2.Comment_Del
import com.example.ssf.retrofit2.Comment_Del_Data
import com.example.ssf.retrofit2.Comment_Del_Output
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CommentListAdapter(val comments: ArrayList<CommentList>,val user:Int,val Context: Context) : RecyclerView.Adapter<CommentListAdapter.viewholder> () {

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
        val P_idx = comments.get(position).P_idx
        val C_idx = comments.get(position).C_idx
        holder.commentMenu.setOnClickListener {
            val fragmentdialog = CommentMenuDialog()
            fragmentdialog.setOnClickListener(object : CommentMenuDialog.OnButtonClickListener{
                override fun onButton1Clicked() {
                    if(user == P_idx){
                        val retrofit = Server_respond()
                        val delcomment = retrofit.create(Comment_Del::class.java)
                        val comment_idx = Comment_Del_Data(C_idx!!)
                        delcomment.requestCommentdel(comment_idx).enqueue(object :
                            Callback<Comment_Del_Output> {
                            override fun onResponse(
                                call: Call<Comment_Del_Output>,
                                response: Response<Comment_Del_Output>
                            ) {
                                Toast.makeText(it.context,"댓글을 삭제했습니다.", Toast.LENGTH_SHORT).show()
                                (Context as CommentActivity).updatelist()


                            }

                            override fun onFailure(call: Call<Comment_Del_Output>, t: Throwable) {
                                Toast.makeText(it.context, "연결 실패.", Toast.LENGTH_SHORT).show()
                            }

                        })
                    }
                    else{
                        Toast.makeText(it.context,"삭제할 수 없는 댓글입니다.", Toast.LENGTH_SHORT).show()

                    }
                }
            })
            fragmentdialog.show((it.context as CommentActivity).supportFragmentManager, "By.김한슬")
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