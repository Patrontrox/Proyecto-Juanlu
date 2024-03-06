package com.juanlu.proyecto_juanlu.API

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.juanlu.proyecto_juanlu.R

class RomsAdapter(val images: List<String>): RecyclerView.Adapter<RomsAdapter.RomViewHolder>() {

    class RomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(image: String) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RomViewHolder(layoutInflater.inflate(R.layout.item_rom, parent, false))
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: RomViewHolder, position: Int) {
        val item = images[position]
        holder.bind(item)
    }
}