package com.misc.superheroapp.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.misc.superheroapp.databinding.ItemAliasBinding

class AliasAdapter : RecyclerView.Adapter<AliasAdapter.AliasViewHolder>() {

    private var listAlias = mutableListOf<String>()

    inner class AliasViewHolder(private val binding: ItemAliasBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(alias: String) {
            binding.root.text = alias
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAlias(alias: List<String>) {
        listAlias.addAll(alias)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AliasViewHolder {
        val binding =
            ItemAliasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AliasViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AliasViewHolder, position: Int) {
        val hero = listAlias[position]
        holder.bind(hero)
    }

    override fun getItemCount(): Int {
        return listAlias.size
    }
}