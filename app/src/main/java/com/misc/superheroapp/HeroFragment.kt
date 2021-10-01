package com.misc.superheroapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misc.superheroapp.data.model.HeroInfoResponse
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
    private var isScrolling = false
    private var isLoading = false

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
        viewModel.enable = true
        initRecyclerView()
        initHeroesObserver()
        viewModel.listHeroes.value?.let {
            if (!it.isNullOrEmpty()) {
                heroesAdapter.updateAll(it)
            } else {
                viewModel.getHeroesInfo()
            }
        }
    }

    private fun seeDetails(it: HeroInfoResponse) {
        val action = HeroFragmentDirections.actionHeroFragmentToHeroDetailFragment(it)
        view?.findNavController()?.navigate(action)
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
            addOnScrollListener(this@HeroFragment.onScrollLister)
        }
        heroesAdapter.setOnItemClickListener { seeDetails(it) }
    }

    private fun initHeroesObserver() {
        viewModel.heroesInfo.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgress()
                    response.data?.let {
                        if(viewModel.listHeroes.value?.contains(it) == false){
                            heroesAdapter.update(it)
                            viewModel.updateListHeroes(it)
                        }
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
        isLoading = true
        binding.progress.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        isLoading = false
        binding.progress.visibility = View.GONE
    }

    private val onScrollLister = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = binding.rvHeroes.layoutManager as LinearLayoutManager
            val sizeOfCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachedToEnd = topPosition + visibleItems >= sizeOfCurrentList
            if (!isLoading && hasReachedToEnd && isScrolling && sizeOfCurrentList >= 5) {
                viewModel.getHeroesInfo()
                isScrolling = false
            }
        }
    }
}