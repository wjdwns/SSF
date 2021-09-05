package com.example.ssf.fragment


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.widget.addTextChangedListener
import com.example.ssf.Activity.SearchListActivity

import com.example.ssf.R
import kotlinx.android.synthetic.main.fragment_search_setting_fragment.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [search_setting_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class search_setting_fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var user : Int? = null
    var job :String? = null
    var ground : String? =null
    var con : String? =null
    val TAG: String = "로그"
    var age :Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            user = it.getInt("유저넘버")



        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var items_job = resources.getStringArray(R.array.spinner_job)
        var myAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,items_job)
        val view = inflater.inflate(R.layout.fragment_search_setting_fragment,container,false)
        val spinner1 = view.findViewById<Spinner>(R.id.spinner1)
        spinner1.adapter = myAdapter
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position){
                    0->{
                        job = null
                    }
                    1->{
                        job = items_job[1]
                    }
                    2->{
                        job = items_job[2]
                    }
                    3->{
                        job = items_job[3]
                    }
                    4->{
                        job = items_job[4]

                    }
                    5->{
                        job = items_job[5]

                    }
                    6->{
                        job = items_job[6]

                    }
                    7->{
                        job = items_job[7]

                    }
                    8->{
                        job = items_job[8]

                    }
                    9->{
                        job = items_job[9]

                    }

                    else->{

                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        val items_ground = resources.getStringArray(R.array.spinner_ground)
        myAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,items_ground)
        val spinner2 = view.findViewById<Spinner>(R.id.spinner2)
        spinner2.adapter = myAdapter
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position){
                    0->{
                        ground = null

                    }
                    1->{
                        ground = items_ground[1]

                    }
                    2->{
                        ground = items_ground[2]

                    }
                    3->{
                        ground = items_ground[3]

                    }
                    4->{
                        ground = items_ground[4]

                    }
                    5->{
                        ground = items_ground[5]

                    }
                    6->{
                        ground = items_ground[6]

                    }
                    7->{
                        ground = items_ground[7]

                    }
                    8->{
                        ground = items_ground[8]

                    }
                    9->{
                        ground = items_ground[9]

                    }
                    10->{
                        ground = items_ground[10]

                    }
                    11->{
                        ground = items_ground[11]

                    }
                    12->{
                        ground = items_ground[12]

                    }
                    13->{
                        ground = items_ground[13]

                    }
                    14->{
                        ground = items_ground[14]

                    }
                    15->{
                        ground = items_ground[15]

                    }
                    16->{
                        ground = items_ground[16]

                    }
                    17->{
                        ground = items_ground[17]

                    }
                    else->{

                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        val items_con = resources.getStringArray(R.array.spinner_detail_ground)
        myAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,items_con)
        val spinner3 = view.findViewById<Spinner>(R.id.spinner3)
        spinner3.adapter = myAdapter
        spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position){
                    0->{
                        con = null
                    }
                    1->{
                        con = items_con[1]
                    }
                    2->{
                        con = items_con[2]
                    }
                    3->{
                        con = items_con[3]
                    }
                    4->{
                        con = items_con[4]
                    }
                    5->{
                        con = items_con[5]
                    }
                    6->{
                        con = items_con[6]
                    }
                    else->{

                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


        // Inflate the layout for this fragment



            return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        search_ok.setOnClickListener{
            val age = edit_age.text.toString().toInt()
            Log.d(TAG, "search_setting_fragment - onCreateView() called"
                    +job + ground + con +age)

            val intent = Intent(context,SearchListActivity::class.java)
            intent.putExtra("유저넘버",user)
            intent.putExtra("job",job)
            intent.putExtra("ground",ground)
            intent.putExtra("condition",con)
            intent.putExtra("age",age)
            intent.putExtra("페이지",4)
            startActivity(intent)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment search_setting_fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            search_setting_fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}