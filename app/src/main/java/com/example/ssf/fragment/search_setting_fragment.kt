package com.example.ssf.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.FragmentManager
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)



        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var items = resources.getStringArray(R.array.spinner_job)
        var myAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,items)
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

                    }
                    1->{

                    }
                    else->{

                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        items = resources.getStringArray(R.array.spinner_job)
        myAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,items)
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

                    }
                    1->{

                    }
                    else->{

                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        items = resources.getStringArray(R.array.spinner_job)
        myAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,items)
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

                    }
                    1->{

                    }
                    else->{

                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        val spinner4 = view.findViewById<Spinner>(R.id.spinner4)
        spinner4.adapter = myAdapter
        spinner4.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

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