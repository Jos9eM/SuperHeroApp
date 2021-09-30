package com.misc.superheroapp.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.misc.superheroapp.data.model.HeroInfoResponse
import com.misc.superheroapp.databinding.HeroListItemBinding
import com.misc.superheroapp.presentation.utils.showGlideImg

@SuppressLint("NotifyDataSetChanged")
class HeroesAdapter() : RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder>() {

    private var listHeroes = mutableListOf<HeroInfoResponse>()

    inner class HeroesViewHolder(private val binding: HeroListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: HeroInfoResponse) {
            if (hero.response == "success") {
                binding.nameHero.text = hero.name
                binding.ivHero.context.showGlideImg(hero.image.url, binding.ivHero)
            }
        }
    }

    fun updateAll(heroes: List<HeroInfoResponse>) {
        listHeroes = heroes as MutableList<HeroInfoResponse>
        notifyDataSetChanged()
    }

    fun update(hero: HeroInfoResponse) {
        listHeroes.add(hero)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val binding =
            HeroListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeroesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        val hero = listHeroes[position]
        holder.bind(hero)
    }

    override fun getItemCount(): Int {
        return listHeroes.size
    }
}