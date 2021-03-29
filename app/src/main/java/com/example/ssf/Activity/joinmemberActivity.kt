package com.example.ssf.Activity

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import com.example.ssf.R
import kotlinx.android.synthetic.main.activity_joinmember.*
import java.util.*

class joinmemberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joinmember)
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)+1
        val day = cal.get(Calendar.DATE)
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
            val toast = Toast.makeText(this,"회원가입이 완료되었습니다.",Toast.LENGTH_SHORT).show()
            val intent = Intent(this,loginActivity::class.java)
            startActivity(intent)
        }

    }
}