package com.example.ssf

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.DatePicker
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
                    date_picker_actions.setText("$year-$month-$day")
                }
            }
            var builder = DatePickerDialog(this, date_listener, year, month, day)
            builder.show()

        }

    }
}