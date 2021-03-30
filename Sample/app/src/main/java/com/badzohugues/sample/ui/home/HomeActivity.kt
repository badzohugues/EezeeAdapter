package com.badzohugues.sample.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.badzohugues.sample.R
import com.badzohugues.sample.databinding.ActivityHomeBinding
import com.badzohugues.sample.misc.SpacingDecoration
import com.badzohugues.sample.model.Person
import com.badzohugues.sample.ui.home.presenter.HomeViewModel
import com.badzohugues.splitzadapter.SplitzAdapter


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private val adapter by lazy { SplitzAdapter<Person>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        observeData()
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initViews() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
    }

    private fun observeData() {
        homeViewModel.buildList()
        homeViewModel.persons.observe(this, { adapter.items = it })
    }

    @SuppressLint("SetTextI18n")
    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val decoration = SpacingDecoration(8)
        adapter.items(ArrayList())
            .layout(R.layout.item_recyclerview)
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

        with(binding) {
            homeRecyclerview.layoutManager = layoutManager
            homeRecyclerview.addItemDecoration(decoration)
            homeRecyclerview.adapter = adapter
        }
    }
}
