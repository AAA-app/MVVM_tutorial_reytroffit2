package com.aaa.mvvm_tutorial_reytroffit2

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.aaa.mvvm_tutorial_reytroffit2.network.RetroInstance
import com.aaa.mvvm_tutorial_reytroffit2.network.RetroService
import kotlinx.android.synthetic.main.activity_recycler_view.*
import retrofit2.Call
import retrofit2.Response

private const val TAG = "RecyclerViewActivity"
class RecyclerViewActivity : AppCompatActivity() {

    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        initRecyclerView()
        createData()
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@RecyclerViewActivity)
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter = recyclerViewAdapter

            val decoration = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(decoration)
        }
    }

    fun createData() {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getDataFromAPI("newyork")
        call.enqueue(object : retrofit2.Callback<RecyclerList> {
            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
                if (response.isSuccessful) {
                    recyclerViewAdapter.setListData(response.body()?.items!!)
                    recyclerViewAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
                Toast.makeText(
                    this@RecyclerViewActivity,
                    "Error in getting data from api.",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
//        val item = ArrayList<RecyclerData>()
//        item.add(RecyclerData("java", "Java description"))
//        item.add(RecyclerData("c++", "C++ description"))
//        item.add(RecyclerData("Android", "Android description"))
//        item.add(RecyclerData("iOS", "iOS description"))
//        item.add(RecyclerData("PHP", "PHP description"))
//        item.add(RecyclerData("Kotlin", "Kotlin description"))
//
//        recyclerViewAdapter.setListData(item)
//        recyclerViewAdapter.notifyDataSetChanged()
    }
}