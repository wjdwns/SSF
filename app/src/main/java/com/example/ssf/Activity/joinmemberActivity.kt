package com.example.ssf.Activity

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import com.example.ssf.R
import com.example.ssf.retrofit2.IJoinMember
import com.example.ssf.retrofit2.JoinMember
import com.example.ssf.retrofit2.JoinMember_Data
import com.example.ssf.retrofit2.LoginService
import kotlinx.android.synthetic.main.activity_joinmember.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class joinmemberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joinmember)
        val TAG: String = "로그"
        var cal = Calendar.getInstance()
        var year = cal.get(Calendar.YEAR)
        var month = cal.get(Calendar.MONTH)+1
        var day = cal.get(Calendar.DATE)
        date_picker_actions.setText("$year-$month-$day")
        date_picker_actions.setOnClickListener {
            var date_listener  = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    val month_1 = month+1
                    date_picker_actions.setText("$year-$month_1-$dayOfMonth")
                }
            }
            var builder = DatePickerDialog(this, date_listener, year, month, day)
            builder.show()

        }
        btn_login.setOnClickListener{
            val nickname = nickname.text.toString()
            val id = edit_id.text.toString()
            val pw = edit_pw.text.toString()
            val birth ="$year-$month-$day"
            Log.d(TAG, "joinmemberActivity - onCreate() called $birth")
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build();
            var retrofit = Retrofit.Builder()
                .baseUrl("http://221.155.173.160:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            val member = JoinMember_Data(nickname, id, pw, birth)
            val JoinMember = retrofit.create(IJoinMember::class.java)

            JoinMember.requestJoinMember(member).enqueue(object : Callback<JoinMember>{
                override fun onResponse(call: Call<JoinMember>, response: Response<JoinMember>) {
                    val res = response.body()
                    if(res?.isOK == true){
                        Toast.makeText(this@joinmemberActivity,"회원가입이 완료되었습니다.",Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@joinmemberActivity,loginActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this@joinmemberActivity,"회원가입이 실패하였습니다. 잠시후 다시 시도해주세요.",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<JoinMember>, t: Throwable) {
                    Toast.makeText(this@joinmemberActivity,"통신 실패",Toast.LENGTH_SHORT).show()

                }

            })
        }

    }
}