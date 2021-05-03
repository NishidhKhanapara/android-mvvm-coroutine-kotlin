package com.nishidhpatel.kotlinstructurecorotines.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nishidhpatel.mvvm.R
import com.nishidhpatel.mvvm.models.Museums.Data
import kotlinx.android.synthetic.main.row_list.view.*


class ListAdapter(private val lists:ArrayList<Data>) : RecyclerView.Adapter<ListAdapter.DataViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_list, parent, false))

    override fun getItemCount(): Int = lists.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(lists[position])
    }

    fun addUsers(list: List<Data>) {
        this.lists.apply {
            clear()
            addAll(list)
        }

    }

    class DataViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(data: Data){
            itemView.apply {
                Glide.with(img.context)
                    .load(data.photo)
                    .into(img)
            }
        }
    }
}