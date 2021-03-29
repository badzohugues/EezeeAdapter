package com.badzohugues.splitzadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

class SplitzAdapter<ITEM> : RecyclerView.Adapter<SplitzAdapter.SplitzViewHolder>() {
    @LayoutRes
    private var layout: Int = 0
    private lateinit var onBind: ((View.(ITEM) -> Unit))
    private var itemClick: ((ITEM.(position: Int) -> Unit)) = { }

    var items: List<ITEM> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SplitzViewHolder {
        return SplitzViewHolder(LayoutInflater.from(parent.context).inflate(layout, parent, false))
    }

    override fun onBindViewHolder(holder: SplitzViewHolder, position: Int) {
        holder.itemView.onBind(items[position])
        holder.itemView.setOnClickListener { itemClick.invoke(items[position], position) }
    }

    override fun getItemCount(): Int = items.size

    fun onBind(onBind: View.(ITEM) -> Unit) = apply { this.onBind = onBind }

    fun items(items: List<ITEM>) = apply { this.items = items }

    fun layout(layout: Int) = apply { this.layout = layout }

    fun itemClick(itemClick: ((ITEM.(position: Int) -> Unit))) = apply { this.itemClick = itemClick }

    class SplitzViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}