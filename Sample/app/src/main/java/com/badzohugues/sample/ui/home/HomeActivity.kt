package com.badzohugues.sample.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.badzohugues.eezeeadapter.EezeeAdapter
import com.badzohugues.sample.R
import com.badzohugues.sample.databinding.ActivityHomeBinding
import com.badzohugues.sample.databinding.ItemRecyclerviewBinding
import com.badzohugues.sample.model.Person
import com.badzohugues.sample.ui.home.presenter.HomePresenter


class HomeActivity : AppCompatActivity(), View {

    private lateinit var binding: ActivityHomeBinding
    private val presenter by lazy { HomePresenter(this) }
    private val eezeeAdapter by lazy { EezeeAdapter<Person, ItemRecyclerviewBinding>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        setContentView(binding.root)
        initRecyclerView()
        presenter.fetchPersons()
    }

    private fun initViews() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
    }

    @SuppressLint("SetTextI18n")
    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val decoration = DividerItemDecoration(this, layoutManager.orientation)

        eezeeAdapter.layout(R.layout.item_recyclerview)
            .items(ArrayList())
            .onBind { item, itemBinding ->
                with(itemBinding) {
                    itemFirstnameTxv.text = "${item.firstname} ${item.lastname}"
                    itemAgeTxv.text = context.getString(R.string.age_text, item.age)
                }
            }
            .itemClick { position ->
                Toast.makeText(
                    this@HomeActivity,
                    "Position $position: ${this.firstname} ${this.lastname}",
                    Toast.LENGTH_SHORT
                ).show()
            }

        with(binding) {
            homeRecyclerview.layoutManager = layoutManager
            homeRecyclerview.addItemDecoration(decoration)
            homeRecyclerview.adapter = eezeeAdapter
        }
    }

    override fun fillPersons(persons: List<Person>) {
        eezeeAdapter.items = persons
    }
}
