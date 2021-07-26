package com.example.ssf.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.ssf.R
import com.example.ssf.retrofit2.CheckLogin
import com.example.ssf.retrofit2.LoginService
import com.example.ssf.retrofit2.login_Data
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class loginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val TAG: String = "로그"
        Log.d(TAG, "loginActivity - onCreate() called")

        //var retrofit = Retrofit.Builder()
        //    .baseUrl("http://221.155.173.160:5000")
        //    .addConverterFactory(GsonConverterFactory.create())
        //    .build()

        //val loginService = retrofit.create(LoginService::class.java)

        btn_login.setOnClickListener{
            var ID = edit_id.text.toString()
            var pwd = edit_pwd.text.toString()
            Log.d(TAG, "loginActivity - btn_login() called")
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build();
            var retrofit = Retrofit.Builder()
                .baseUrl("http://221.155.173.160:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            val login = login_Data(ID,pwd)
            val loginService = retrofit.create(LoginService::class.java)

            loginService.requestLogin(login).enqueue(object : Callback<login_Data>{
                override fun onResponse(call: Call<login_Data>, response: Response<login_Data>) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<login_Data>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
            if()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        joinmem.setOnClickListener{
            Log.d(TAG, "loginActivity - joinmem() called")
            val intent = Intent(this, joinmemberActivity::class.java)
            startActivity(intent)

        }
        nomem.setOnClickListener{
            Log.d(TAG, "loginActivity - nomem() called")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}

