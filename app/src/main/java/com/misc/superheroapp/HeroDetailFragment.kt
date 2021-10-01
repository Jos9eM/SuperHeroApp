package com.misc.superheroapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.misc.superheroapp.data.model.HeroInfoResponse
import com.misc.superheroapp.databinding.FragmentHeroDetailBinding
import com.misc.superheroapp.presentation.utils.showGlideImg

class HeroDetailFragment : Fragment() {

    private lateinit var binding: FragmentHeroDetailBinding
    private lateinit var hero: HeroInfoResponse
    private val args: HeroDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHeroDetailBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        hero = args.heroObject
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        with(binding){
            context?.showGlideImg(hero.image.url, ivHero)
            heroName.text = hero.name
            powerStats.combat.intelligence.text = "Combate:"
            powerStats.intelligence.intelligence.text = "Inteligencia:"
            powerStats.speed.intelligence.text = "Velocidad:"
            powerStats.strength.intelligence.text = "Fuerza:"
            powerStats.power.intelligence.text = "Poder:"
            powerStats.durability.intelligence.text = "Durabilidad:"
            powerStats.durability.rateIntelligence.rating = if (hero.powerstats.durability != "null")  ((hero.powerstats.durability.toFloat()*5)/100) else 0f
            powerStats.power.rateIntelligence.rating = if (hero.powerstats.power != "null") ((hero.powerstats.power.toFloat()*5)/100) else 0f
            powerStats.strength.rateIntelligence.rating =  if (hero.powerstats.strength != "null") ((hero.powerstats.strength.toFloat()*5)/100) else 0f
            powerStats.speed.rateIntelligence.rating =  if (hero.powerstats.speed != "null") ((hero.powerstats.speed.toFloat()*5)/100) else 0f
            powerStats.intelligence.rateIntelligence.rating = if (hero.powerstats.intelligence != "null") ((hero.powerstats.intelligence.toFloat()*5)/100) else 0f
            powerStats.combat.rateIntelligence.rating = if (hero.powerstats.combat != "null") ((hero.powerstats.combat.toFloat()*5)/100) else 0f
            hero.biography.let {
                fullName.text = it.fullName
                alterEgos.text = it.alterEgos
            }
        }

    }
}