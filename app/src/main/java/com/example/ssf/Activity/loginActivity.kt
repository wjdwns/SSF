package com.example.ssf.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.ssf.R
import com.example.ssf.retrofit2.LoginData
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

        var retrofit = Retrofit.Builder()
            .baseUrl("http://221.155.173.160:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val loginService = retrofit.create(LoginService::class.java)

        btn_login.setOnClickListener{
            var ID = edit_id.text.toString()
            var pwd = edit_pwd.text.toString()
            loginService.requestLogin(ID,pwd).enqueue(object: Callback<LoginData>{
                override fun onResponse(call: Call<LoginData>, response: Response<LoginData>) {
                    var login = response.body()
                    Toast.makeText(this@loginActivity,"code = " + login?.code + "msg = " + login?.msg, Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<LoginData>, t: Throwable) {
                    Toast.makeText(this@loginActivity,"실패!", Toast.LENGTH_SHORT).show()
                }

            })
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        joinmem.setOnClickListener{
            val intent = Intent(this, joinmemberActivity::class.java)
            startActivity(intent)
        }
        nomem.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}

