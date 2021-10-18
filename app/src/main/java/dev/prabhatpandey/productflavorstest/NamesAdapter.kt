package dev.prabhatpandey.productflavorstest

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Created By Prabhat Pandey for ProductFlavorsTest project
 * on Monday, 18 October, 2021 at 8:06 AM
 */

class NamesAdapter(nameList: List<Name>) : RecyclerView.Adapter<NamesAdapter.NameViewHolder>() {

    private var list = nameList

    class NameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val nameTv : TextView = itemView.findViewById(R.id.name)
        private val numberTv : TextView = itemView.findViewById(R.id.number)

        fun bind(position: Int, name: Name) {
            nameTv.text = name.title
            val number = (position + 1).toString()
            numberTv.text = number
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return NameViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        val name = list[position]
        holder.bind(position, name)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNameList(nameList: List<Name>) {
        list = nameList
        notifyDataSetChanged()
    }
}