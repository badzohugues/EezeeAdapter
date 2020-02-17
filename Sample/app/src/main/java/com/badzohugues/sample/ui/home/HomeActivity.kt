package com.badzohugues.sample.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.badzohugues.eezeeadapter.EezeeAdapter
import com.badzohugues.sample.R
import com.badzohugues.sample.model.Person
import com.badzohugues.sample.ui.home.presenter.HomePresenter


class HomeActivity : AppCompatActivity(), View {

    private lateinit var recyclerV: RecyclerView
    private val presenter by lazy { HomePresenter(this) }
    private val eezeeAdapter by lazy { EezeeAdapter<Person>() }

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

    @SuppressLint("SetTextI18n")
    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val decoration = DividerItemDecoration(this, layoutManager.orientation)

        eezeeAdapter.layout(R.layout.item_recyclerview)
            .items(ArrayList())
            .onBind { item ->
                val firstNameTxv: TextView = findViewById(R.id.item_firstname_txv)
                val ageTxv: TextView = findViewById(R.id.item_age_txv)
                val context= this.context

                firstNameTxv.text = "${item.firstname} ${item.lastname}"
                ageTxv.text = context.getString(R.string.age_text, item.age)
            }
            .itemClick {  position ->
                Toast.makeText(this@HomeActivity, "Position $position: ${this.firstname} ${this.lastname}", Toast.LENGTH_SHORT).show()
            }

        recyclerV.layoutManager = layoutManager
        recyclerV.addItemDecoration(decoration)
        recyclerV.adapter = eezeeAdapter
    }

    override fun fillPersons(persons: List<Person>) { eezeeAdapter.items = persons }
}
