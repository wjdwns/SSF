package com.example.ssf.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ssf.Activity.Keyword_searchActivity
import com.example.ssf.Activity.SearchListActivity
import com.example.ssf.Adapter.SearchlistAdapter
import com.example.ssf.List.itemList
import com.example.ssf.R
import kotlinx.android.synthetic.main.fragment_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [home_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class home_fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            val getbundle = arguments?.getInt("유저넘버")
            val TAG: String = "로그"
            Log.d(TAG, "home_fragment - onCreate() called $getbundle")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        main_search.setOnClickListener{
            activity?.let{
                val intent = Intent(context,Keyword_searchActivity::class.java)
                val getbundle = arguments?.getInt("유저넘버")
                intent.putExtra("유저넘버", getbundle)
                startActivity(intent)
            }
        }



    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val realItems = arrayListOf(
            itemList("국가장학금",  "♥ 56","www.",1,1),
            itemList("숭실대학교 백마우수어쩌구",  "♥ 90","www.",1,1),
            itemList("경기도 2차 재난지원금",  "♥ 27","www.",1,1),
            itemList("청년창업지원금",  "♥ 8","www.",1,1))

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.main_recyclerview1)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = SearchlistAdapter(realItems)
        val decoDivider = DividerItemDecoration(recyclerView.context, 1)
        recyclerView.addItemDecoration(decoDivider)

        recyclerView.setHasFixedSize(true)

        val recyclerView2: RecyclerView = view.findViewById(R.id.main_recyclerview2)
        recyclerView2.layoutManager = LinearLayoutManager(activity)
        recyclerView2.adapter = SearchlistAdapter(realItems)
        val decoDivider2 = DividerItemDecoration(recyclerView2.context, 1)
        recyclerView2.addItemDecoration(decoDivider2)

        recyclerView2.setHasFixedSize(true)

        val more_view =view.findViewById<TextView>(R.id.main_moreview)
        val more_view2 =view.findViewById<TextView>(R.id.main_moreview2)
        more_view.setOnClickListener {
            val intent = Intent(activity, SearchListActivity::class.java)
            startActivity(intent)
        }
        more_view2.setOnClickListener{
            val intent = Intent(activity, SearchListActivity::class.java)
            startActivity(intent)
        }

        return view





        //return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment home_fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            home_fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}