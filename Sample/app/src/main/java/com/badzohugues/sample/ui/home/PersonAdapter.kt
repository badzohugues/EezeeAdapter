package com.badzohugues.sample.ui.home

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.badzohugues.sample.R
import com.badzohugues.sample.misc.inflate
import com.badzohugues.sample.model.Person

class PersonAdapter : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    var items: MutableList<Person> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder(parent.inflate(R.layout.item_recyclerview))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = items[position]
        val context= holder.itemView.context

        holder.firstnameTxv.text = "${person.firstname} ${person.lastname}"
        holder.ageTxv.text = context.getString(R.string.age_text, person.age)
    }

    override fun getItemCount(): Int = items.size

    inner class PersonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val firstnameTxv: TextView = view.findViewById(R.id.item_firstname_txv)
        val ageTxv: TextView = view.findViewById(R.id.item_age_txv)
    }
}