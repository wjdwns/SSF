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


        date_picker_actions.setOnClickListener {
            val cal = Calendar.getInstance()
            DatePickerDialog(this,DatePickerDialog.OnDateSetListener{
                datePicker,y,m,d->
            })
        }

    }
}