package com.misc.superheroapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misc.superheroapp.data.utils.Resource
import com.misc.superheroapp.databinding.FragmentHeroBinding
import com.misc.superheroapp.presentation.adapter.HeroesAdapter
import com.misc.superheroapp.presentation.utils.isLandscape
import com.misc.superheroapp.presentation.utils.showToast
import com.misc.superheroapp.presentation.viewmodel.HeroesInfoViewModel

class HeroFragment : Fragment() {

    private lateinit var binding: FragmentHeroBinding
    private lateinit var viewModel: HeroesInfoViewModel

    private lateinit var heroesAdapter: HeroesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        initRecyclerView()
        initHeroesObserver()
        viewModel.enable = true
        viewModel.listHeroes.value?.let {
            if (!it.isNullOrEmpty()) {
                heroesAdapter.updateAll(it)
            } else {
                viewModel.getHeroesInfo()
            }
        }
    }

    private fun initRecyclerView() {
        heroesAdapter = HeroesAdapter()
        binding.rvHeroes.apply {
            adapter = heroesAdapter
            layoutManager = if (context?.isLandscape() == true) {
                GridLayoutManager(activity, 2)
            } else {
                GridLayoutManager(activity, 4)
            }
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val layoutManager =
                        LinearLayoutManager::class.java.cast(recyclerView.layoutManager)
                    val totalItemCount = layoutManager.itemCount
                    val lastVisible = layoutManager.findLastVisibleItemPosition()
                    val endHasBeenReached = lastVisible + 5 >= totalItemCount
                    if (totalItemCount >= 10 && endHasBeenReached) {
                        viewModel.getHeroesInfo()
                    }
                }
            })
        }
    }

    private fun initHeroesObserver() {
        viewModel.heroesInfo.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgress()
                    response.data?.let {
                        heroesAdapter.update(it)
                        viewModel.updateListHeroes(it)
                    }
                }
                is Resource.Loading -> {
                    showProgress()
                }
                is Resource.Error -> {
                    hideProgress()
                    response.message?.let {
                        context?.showToast(it)
                    }
                }
            }
        })
    }

    private fun showProgress() {
        binding.progress.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.progress.visibility = View.GONE
    }
}