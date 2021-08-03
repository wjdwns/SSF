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

            loginService.requestLogin(login).enqueue(object : Callback<CheckLogin>{
                override fun onResponse(call: Call<CheckLogin>, response: Response<CheckLogin>) {
                    val res = response.body()
                    if(res?.isValid==true)
                    {
                        Toast.makeText(this@loginActivity,"로그인이 완료되었습니다.\n" + "닉네임" + res.nickname +"\n생일"+ res.birth + "\n유저 넘버 : " + res.usernum,Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@loginActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this@loginActivity,"아이디나 비밀번호가 다릅니다.",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<CheckLogin>, t: Throwable) {
                    Toast.makeText(this@loginActivity,"연결 실패.",Toast.LENGTH_SHORT).show()
                }


            })

        }
        joinmem.setOnClickListener{
            Log.d(TAG, "loginActivity - joinmem() called")
            Toast.makeText(this@loginActivity,"비회원으로 입장하셨습니다.",Toast.LENGTH_SHORT).show()
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

