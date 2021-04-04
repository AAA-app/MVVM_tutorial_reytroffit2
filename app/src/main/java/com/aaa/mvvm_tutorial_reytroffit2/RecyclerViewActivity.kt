package com.aaa.mvvm_tutorial_reytroffit2

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.aaa.mvvm_tutorial_reytroffit2.adapter.RecyclerViewAdapter
import com.aaa.mvvm_tutorial_reytroffit2.data.RecyclerList
import com.aaa.mvvm_tutorial_reytroffit2.viewmodel.RecyclerViewModel
import kotlinx.android.synthetic.main.activity_recycler_view.*


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
            addItemDecoration(decoration) //  recyclerViewlayout razdelyat linear
        }
    }

    private fun createData() {

        val viewModel = ViewModelProvider (this).get(RecyclerViewModel :: class.java)
        viewModel.getRecyclerListDataObserver().observe(this, Observer<RecyclerList>{
            if (it != null) {
                recyclerViewAdapter.setListData(it.items)
                recyclerViewAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this@RecyclerViewActivity, "Error in getting data from api.", Toast.LENGTH_LONG).show()
            }

        })

        searchButton.setOnClickListener {
            viewModel.makeApiCall(searchBox.text.toString())
        }
    }
}