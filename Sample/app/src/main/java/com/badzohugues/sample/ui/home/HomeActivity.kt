package com.badzohugues.sample.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.badzohugues.sample.R
import com.badzohugues.sample.model.Person
import com.badzohugues.sample.ui.home.presenter.HomePresenter


class HomeActivity : AppCompatActivity(), View {

    private val personsAdapter by lazy { PersonAdapter() }
    private lateinit var recyclerV: RecyclerView
    private val presenter by lazy { HomePresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initViews()
        initRecyclerView()
        presenter.fetchPersons()
    }

    private fun initViews() {
        recyclerV = findViewById(R.id.home_recyclerview)
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val decoration = DividerItemDecoration(this, layoutManager.orientation)

        recyclerV.layoutManager = layoutManager
        recyclerV.addItemDecoration(decoration)
        recyclerV.adapter = personsAdapter
    }

    override fun fillPersons(persons: List<Person>) {
        personsAdapter.items = persons.toMutableList()
    }
}
