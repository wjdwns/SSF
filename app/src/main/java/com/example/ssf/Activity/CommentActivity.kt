package com.example.ssf.Activity


import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ssf.Adapter.CommentListAdapter
import com.example.ssf.Dialog.CommentMenuDialog
import com.example.ssf.List.CommentList
import com.example.ssf.R
import com.example.ssf.retrofit2.*
import kotlinx.android.synthetic.main.activity_comment.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CommentActivity : AppCompatActivity() {
    var user: Int? = null
    var ALL_idx: Int? = null
    val TAG: String = "로그"
    val retrofit = Server_respond()
    //private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)
        user = intent.getIntExtra("유저넘버", 0)
        ALL_idx = intent.getIntExtra("ALL_idx", 0)
        updatelist()
        btn_send.setOnClickListener {
            val comment = findViewById<EditText>(R.id.et_writeComment).text.toString()
            val commentadd = Comment_Data(comment, user!!, ALL_idx!!)
            val CommentService = retrofit.create(CommentService::class.java)
            CommentService.requestComment(commentadd).enqueue(object : Callback<Comment_Output> {
                override fun onResponse(
                    call: Call<Comment_Output>,
                    response: Response<Comment_Output>
                ) {
                    val res = response.body()
                    if (res != null) {
                        if (res.isOK) {
                            Log.d(TAG, "CommentActivity - onResponse() called")
                            try {
                                //TODO 액티비티 화면 재갱신 시키는 코드
                                val intent = intent
                                finish() //현재 액티비티 종료 실시
                                overridePendingTransition(0, 0) //인텐트 애니메이션 없애기
                                startActivity(intent) //현재 액티비티 재실행 실시
                                overridePendingTransition(0, 0) //인텐트 애니메이션 없애기
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    }
                }
                override fun onFailure(call: Call<Comment_Output>, t: Throwable) {
                    Toast.makeText(this@CommentActivity, "연결실패", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
    override fun onResume(){
        super.onResume()
        Log.d(TAG, "CommentActivity - onResume() called")

    }



    fun updatelist(){
        Log.d(TAG, "CommentActivity - updatelist() called")
        val all = CommentList_Data(ALL_idx!!)
        val CommentListService = retrofit.create(CommentListService::class.java)
        CommentListService.requestCommentList(all).enqueue(object :
            Callback<List<CommentList_Output>> {
            override fun onResponse(
                call: Call<List<CommentList_Output>>,
                response: Response<List<CommentList_Output>>
            ) {
                val res = response.body()
                val commentList = ArrayList<CommentList>()
                for (i in 1..res?.size!!) {
                    commentList.add(
                        CommentList(
                            res?.get(i - 1).nickname,
                            res?.get(i - 1).Comment,
                            res?.get(i - 1).P_idx,
                            res?.get(i - 1).C_idx

                        )
                    )
                }
                rv_comment.layoutManager = LinearLayoutManager(
                    this@CommentActivity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                rv_comment.adapter = CommentListAdapter(commentList,user!!,this@CommentActivity)
                val decoDivider = DividerItemDecoration(rv_comment.context, 1)
                rv_comment.addItemDecoration(decoDivider)
                rv_comment.setHasFixedSize(true)
            }

            override fun onFailure(call: Call<List<CommentList_Output>>, t: Throwable) {
                Toast.makeText(this@CommentActivity, "연결 실패.", Toast.LENGTH_SHORT).show()
            }

        }
        )
    }
    fun Server_respond(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build();
        var retrofit = Retrofit.Builder()
            .baseUrl("http://221.155.173.160:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit
    }

}