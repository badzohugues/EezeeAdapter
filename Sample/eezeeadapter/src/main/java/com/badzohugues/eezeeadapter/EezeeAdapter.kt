package com.badzohugues.eezeeadapter

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

class EezeeAdapter<ITEM> : RecyclerView.Adapter<EezeeAdapter.EezeeViewHolder>() {
    @LayoutRes
    private var layout: Int = 0
    private lateinit var onBind: ((View.(ITEM) -> Unit))
    private var itemClick: ((ITEM.(position: Int) -> Unit)) = { }

    var items: List<ITEM> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EezeeViewHolder {
        return EezeeViewHolder(parent.inflate(layout))
    }

    override fun onBindViewHolder(holder: EezeeViewHolder, position: Int) {
        holder.itemView.onBind(items[position])
        holder.itemView.setOnClickListener { itemClick.invoke(items[position], position) }
    }

    override fun getItemCount(): Int = items.size

    fun onBind(onBind: View.(ITEM) -> Unit) = apply { this.onBind = onBind }

    fun items(items: List<ITEM>) = apply { this.items = items }

    fun layout(layout: Int) = apply { this.layout = layout }

    fun itemClick(itemClick: ((ITEM.(position: Int) -> Unit))) = apply { this.itemClick = itemClick }

    class EezeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}