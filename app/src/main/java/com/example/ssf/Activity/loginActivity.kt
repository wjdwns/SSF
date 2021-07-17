package com.example.ssf.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.ssf.R
import com.example.ssf.retrofit2.CheckLogin
import com.example.ssf.retrofit2.LoginService
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class loginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val TAG: String = "로그"
        Log.d(TAG, "loginActivity - onCreate() called")

        var retrofit = Retrofit.Builder()
            .baseUrl("http://221.155.173.160:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val loginService = retrofit.create(LoginService::class.java)

        btn_login.setOnClickListener{
            var ID = edit_id.text.toString()
            var pwd = edit_pwd.text.toString()
            Log.d(TAG, "loginActivity - btn_login() called")
            loginService.requestLogin(ID,pwd).enqueue(object: Callback<CheckLogin>{
                override fun onResponse(call: Call<CheckLogin>, response: Response<CheckLogin>) {
                    var login = response.body()
                    Toast.makeText(this@loginActivity,"isValid = " + login?.isValid + "nickname = " + login?.nickname +"birth =" + login?.birth, Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<CheckLogin>, t: Throwable) {
                    Toast.makeText(this@loginActivity,"실패!", Toast.LENGTH_SHORT).show()
                }

            })

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

