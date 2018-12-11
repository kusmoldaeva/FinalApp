package com.example.akniyet.finalapp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item.view.*


class Adapter(private val context : Context,
              private val items : ArrayList<Item>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        var contact = items[position]

        position.itemView.textID.text = contact.ID

        position.itemView.textName.text = contact.name
        position.itemView.textNumber.text = contact.number
        position.itemView.textHome.text = contact.home
        position.itemView.textWork.text = contact.work
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return ItemsViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false))
    }

    class ItemsViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView)
}