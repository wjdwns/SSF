package com.example.ssf.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ssf.Adapter.CartListAdapter
import com.example.ssf.R
import kotlinx.android.synthetic.main.fragment_cart_fragment.*

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
        val view = inflater.inflate(R.layout.fragment_cart_fragment, container, false)
        val rv_cartlist = view.findViewById<RecyclerView>(R.id.rv_cartlist)
        val cartItems = arrayOf (
            "쿠키런 킹덤 4000크리스탈 지원금",
            "어쩌구 제목 지원금",
            "엥엥엥 쿠쿠루삥뽕빵~"
        )

        rv_cartlist.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rv_cartlist.adapter = CartListAdapter(cartItems)
        rv_cartlist.setHasFixedSize(true)




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