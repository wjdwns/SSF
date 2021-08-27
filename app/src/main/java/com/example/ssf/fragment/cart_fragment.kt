package com.example.ssf.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ssf.Adapter.CartListAdapter
import com.example.ssf.List.CartList
import com.example.ssf.List.ItemList
import com.example.ssf.R
import com.example.ssf.retrofit2.Cart_Input
import com.example.ssf.retrofit2.Cart_ListService
import com.example.ssf.retrofit2.Cart_Output
import kotlinx.android.synthetic.main.activity_searchlist.*
import kotlinx.android.synthetic.main.fragment_cart_fragment.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [cart_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class cart_fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val TAG: String = "로그"
        Log.d(TAG, "cart_fragment - onCreate() called")
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val TAG: String = "로그"
        val view = inflater.inflate(R.layout.fragment_cart_fragment, container, false)
        val rv_cartlist = view.findViewById<RecyclerView>(R.id.rv_cartlist)
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build();
        var retrofit = Retrofit.Builder()
            .baseUrl("http://221.155.173.160:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        val usernum = arguments?.getInt("유저넘버")
        val Input = Cart_Input(usernum!!)
        Log.d(TAG, "cart_fragment - onCreateView() called $usernum")
        val CartService = retrofit.create(Cart_ListService::class.java)
        CartService.requestCart(Input).enqueue(object : Callback<List<Cart_Output>> {
            override fun onResponse(
                call: Call<List<Cart_Output>>,
                response: Response<List<Cart_Output>>
            ) {
                Toast.makeText(context, "연결성공", Toast.LENGTH_SHORT).show()
                val res = response.body()
                println(res?.size)
                var realItems = ArrayList<ItemList>()
                for (i in 1..res?.size!!) {
                    realItems.add(
                        ItemList(
                            res?.get(i - 1).title,
                            res?.get(i - 1).host,
                            res?.get(i - 1).href,
                            usernum,
                            res?.get(i - 1).all_idx
                        )
                    )
                }
                rv_cartlist.layoutManager = LinearLayoutManager(activity)
                rv_cartlist.adapter = CartListAdapter(realItems)
                rv_cartlist.setHasFixedSize(true)
            }

            override fun onFailure(call: Call<List<Cart_Output>>, t: Throwable) {
                Toast.makeText(context, "연결실패", Toast.LENGTH_SHORT).show()
            }

        }
        )
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment cart_fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            cart_fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}