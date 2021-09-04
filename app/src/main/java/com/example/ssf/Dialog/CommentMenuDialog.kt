package com.example.ssf.Dialog

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import com.example.ssf.Activity.CommentActivity
import com.example.ssf.R
import com.example.ssf.retrofit2.Comment_Del
import com.example.ssf.retrofit2.Comment_Del_Data
import com.example.ssf.retrofit2.Comment_Del_Output

import kotlinx.android.synthetic.main.dialog_comment_menu.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CommentMenuDialog : DialogFragment () {

    val TAG: String = "로그"
    private lateinit var buttonClickListener: OnButtonClickListener
    fun setOnClickListener(buttonClickListener: OnButtonClickListener) {
        this.buttonClickListener = buttonClickListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_comment_menu, container, false)
        val tv_del = view.findViewById<TextView>(R.id.tv_delete)
        val tv_can = view.findViewById<TextView>(R.id.tv_cancel)
        Log.d(TAG, "CommentMenuDialog - onCreateView() called")
        tv_del.setOnClickListener {
            buttonClickListener.onButton1Clicked()
            //(activity as CommentActivity).updatelist()
            dismiss()
        }
        tv_can.setOnClickListener {
            dismiss()
        }
        return view


        //return super.onCreateView(inflater, container, savedInstanceState)
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
    interface OnButtonClickListener{
        fun onButton1Clicked()

    }


}