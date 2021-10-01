package com.misc.superheroapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.misc.superheroapp.data.model.HeroInfoResponse
import com.misc.superheroapp.databinding.FragmentHeroDetailBinding
import com.misc.superheroapp.presentation.adapter.AliasAdapter
import com.misc.superheroapp.presentation.utils.*

class HeroDetailFragment : Fragment() {

    private lateinit var binding: FragmentHeroDetailBinding
    private lateinit var hero: HeroInfoResponse
    private lateinit var aliasAdapter: AliasAdapter
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
        try {
            with(binding) {
                context?.showGlideImg(hero.image.url, ivHero)
                heroName.text = hero.name
                powersTitle.text = getString(R.string.stats)
                aliases.text = getString(R.string.aliases)

                aliasAdapter = AliasAdapter()
                binding.rvAliases.apply {
                    adapter = aliasAdapter
                    layoutManager = LinearLayoutManager(activity)
                }

                hero.biography.let {
                    fullName.text = it.fullName
                    alterEgos.text = it.alterEgos
                    aliasAdapter.setAlias(it.aliases)
                    birthPlace.textHtml(getString(R.string.birthPlace), it.placeBirth)
                    firstAppearance.textHtml(getString(R.string.firstApp), it.firstAppearance)
                    publisher.textHtml(getString(R.string.published), it.publisher)
                    alignment.setAlignment(it.alignment.uppercase())
                }

                with(powerStats) {
                    combat.stat.text = getString(R.string.combat)
                    intelligence.stat.text = getString(R.string.intelligence)
                    speed.stat.text = getString(R.string.Speed)
                    strength.stat.text = getString(R.string.Strength)
                    power.stat.text = getString(R.string.Power)
                    durability.stat.text = getString(R.string.Durability)
                    hero.powerstats.let {
                        combat.rate.showRate(it.combat)
                        intelligence.rate.showRate(it.intelligence)
                        speed.rate.showRate(it.speed)
                        strength.rate.showRate(it.strength)
                        power.rate.showRate(it.power)
                        durability.rate.showRate(it.durability)
                    }
                }

                hero.appearance.let {
                    gender.setGender(it.gender)
                    race.textHtml(getString(R.string.race), it.race)
                    height.textHtml(getString(R.string.height), it.height[1])
                    weight.textHtml(getString(R.string.weight), it.weight[1])
                    eyes.textHtml(getString(R.string.eyes), it.eyeColor)
                    hair.textHtml(getString(R.string.hair), it.hairColor)
                }

                hero.work.let {
                    occupation.textHtml(getString(R.string.occupation), it.occupation)
                    base.textHtml(getString(R.string.base), it.base)
                }

                hero.connections.let {
                    groups.textHtml(getString(R.string.groups), it.groupAffiliation)
                    relatives.textHtml(getString(R.string.relatives), it.relatives)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}