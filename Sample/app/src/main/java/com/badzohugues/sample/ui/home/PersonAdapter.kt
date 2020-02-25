package com.badzohugues.sample.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.badzohugues.sample.R
import com.badzohugues.sample.databinding.ItemRecyclerviewBinding
import com.badzohugues.sample.model.Person

class PersonAdapter : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {
    var items: MutableList<Person> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = ItemRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return PersonViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = items[position]
        val context= holder.itemView.context

        holder.firstnameTxv.text = "${person.firstname} ${person.lastname}"
        holder.ageTxv.text = context.getString(R.string.age_text, person.age)
    }

    override fun getItemCount(): Int = items.size

    inner class PersonViewHolder(binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        val firstnameTxv = binding.itemFirstnameTxv
        val ageTxv = binding.itemAgeTxv
    }
}