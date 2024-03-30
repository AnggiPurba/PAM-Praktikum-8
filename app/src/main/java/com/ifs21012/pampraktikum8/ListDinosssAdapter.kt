package com.ifs21012.pampraktikum8

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21012.pampraktikum8.databinding.ItemRowDinoBinding

class ListDinosssAdapter(private val listDinosss: ArrayList<Dinosss>) :
    RecyclerView.Adapter<ListDinosssAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowDinoBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val dinosss = listDinosss[position]
        holder.binding.ivItemDinosss.setImageResource(dinosss.icon)
        holder.binding.tvItemDinossss.text = dinosss.name
        holder.binding.tvItemDinossss2.text = dinosss.Kelompok
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listDinosss[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listDinosss.size

    inner class ListViewHolder(val binding: ItemRowDinoBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Dinosss)
    }
}
