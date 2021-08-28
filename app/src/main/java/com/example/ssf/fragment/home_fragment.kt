package com.example.ssf.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ssf.Activity.Keyword_searchActivity
import com.example.ssf.Activity.SearchListActivity
import com.example.ssf.Adapter.SearchlistAdapter
import com.example.ssf.List.ItemList
import com.example.ssf.R
import com.example.ssf.retrofit2.New_Service
import com.example.ssf.retrofit2.PoPular_Service
import com.example.ssf.retrofit2.grant_form
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
    var user : Int? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            user = it.getInt("유저넘버")
            val getbundle = arguments?.getInt("유저넘버")
            val TAG: String = "로그"
            Log.d(TAG, "home_fragment - onCreate() called $getbundle")
            val intent = Intent(context,Keyword_searchActivity::class.java)
            intent.putExtra("유저넘버", getbundle)

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        main_search.setOnClickListener{
            activity?.let{
                val TAG: String = "로그"
                val intent = Intent(context,Keyword_searchActivity::class.java)
                Log.d(TAG, "home_fragment - onActivityCreated() called" + user)
                intent.putExtra("유저넘버", user)
                activity!!.startActivity(intent)
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val TAG: String = "로그"
        val getbundle = arguments?.getInt("유저넘버")

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        Log.d(TAG, "home_fragment - onCreateView() called" + getbundle)
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build();
        var retrofit = Retrofit.Builder()
            .baseUrl("http://221.155.173.160:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        val PopularService = retrofit.create(PoPular_Service::class.java)
        PopularService.requestPoPular().enqueue(object : Callback<List<grant_form>> {
            override fun onResponse(
                call: Call<List<grant_form>>,
                response: Response<List<grant_form>>
            ) {
                Toast.makeText(context, "연결성공", Toast.LENGTH_SHORT).show()
                val res = response.body()
                var realItems = ArrayList<ItemList>()

                for (i in 1..res?.size!!) {
                    realItems.add(
                        ItemList(
                            res?.get(i - 1).title,
                            res?.get(i - 1).host,
                            res?.get(i - 1).href,
                            user!!,
                            res?.get(i - 1).all_idx
                        )
                    )
                }
                val recyclerView: RecyclerView = view.findViewById(R.id.main_recyclerview1)
                recyclerView.layoutManager = LinearLayoutManager(activity)
                recyclerView.adapter = SearchlistAdapter(realItems)
                val decoDivider = DividerItemDecoration(recyclerView.context, 1)
                recyclerView.addItemDecoration(decoDivider)
                recyclerView.setHasFixedSize(true)
            }

            override fun onFailure(call: Call<List<grant_form>>, t: Throwable) {
                Toast.makeText(context, "연결실패", Toast.LENGTH_SHORT).show()
            }

        })


        val newService = retrofit.create(New_Service::class.java)
        newService.requestNew().enqueue(object : Callback<List<grant_form>>{
            override fun onResponse(
                call: Call<List<grant_form>>,
                response: Response<List<grant_form>>
            ) {
                Toast.makeText(context, "연결성공", Toast.LENGTH_SHORT).show()
                val res = response.body()
                var realItems = ArrayList<ItemList>()
                for (i in 1..res?.size!!) {
                    realItems.add(
                        ItemList(
                            res?.get(i - 1).title,
                            res?.get(i - 1).host,
                            res?.get(i - 1).href,
                            user!!,
                            res?.get(i - 1).all_idx
                        )
                    )
                }
                val recyclerView2: RecyclerView = view.findViewById(R.id.main_recyclerview2)
                recyclerView2.layoutManager = LinearLayoutManager(activity)
                recyclerView2.adapter = SearchlistAdapter(realItems)
                val decoDivider2 = DividerItemDecoration(recyclerView2.context, 1)
                recyclerView2.addItemDecoration(decoDivider2)
                recyclerView2.setHasFixedSize(true)
            }

            override fun onFailure(call: Call<List<grant_form>>, t: Throwable) {
                Toast.makeText(context, "연결실패", Toast.LENGTH_SHORT).show()
            }

        })


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