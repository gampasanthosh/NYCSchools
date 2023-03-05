package com.example.nycschools.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nycschools.databinding.ItemViewBinding
import com.example.nycschools.models.SchoolSatScores

class RVAdapter(private val context: Context,
                private val list : List<SchoolSatScores>,
                private val itemClickedInterface: onItemClickedInterface) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ItemViewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = ItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            val currentItem = list[position]
            tvName.text = currentItem.school_name
        }

        holder.binding.root.setOnClickListener{
            itemClickedInterface.onItemClick(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

interface onItemClickedInterface{
    fun onItemClick(listItems: SchoolSatScores)
}