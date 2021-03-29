package com.example.ssf.fragment

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ssf.R
import kotlinx.android.synthetic.main.search_setting_fragment1.*

class search_setting_fragment1 :Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val context : Context = container!!.getContext()
        val items = resources.getStringArray(R.array.spinner_job)
        val myAdapter = object : ArrayAdapter<String>(context, R.layout.search_setting_fragment1){

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

                val view = super.getView(position, convertView, parent)

                if(position == count){
                    (spinner1 as TextView).text = ""
                    (spinner1 as TextView).hint=getItem(count)

                }
                return view
            }

            override fun getCount(): Int {
                return super.getCount()-1
            }
        }
        myAdapter.addAll(items.toMutableList())
        myAdapter.add("직업")
        spinner1.adapter = myAdapter
        spinner1.setSelection(myAdapter.count)
        spinner1.dropDownVerticalOffset = dipToPixels(45f).toInt()
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position){
                    0->{

                    }
                    1->{

                    }
                    //...
                    else->{

                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        return if (inflater != null) {
            inflater.inflate(R.layout.search_setting_fragment1, container, false)
        } else {
            return super.onCreateView(inflater, container, savedInstanceState)
        }
    }
    fun dipToPixels(dipValue:Float):Float{
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dipValue,
            resources.displayMetrics
        )
    }
}
