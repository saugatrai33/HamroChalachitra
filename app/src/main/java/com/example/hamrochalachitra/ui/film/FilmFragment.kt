package com.example.hamrochalachitra.ui.film

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hamrochalachitra.R
import com.example.hamrochalachitra.databinding.FragmentFilmBinding
import com.example.hamrochalachitra.ui.MainViewModel
import com.example.hamrochalachitra.utils.Constants.KEY_FILM_ID
import com.example.hamrochalachitra.utils.Utility.hideProgress
import com.example.hamrochalachitra.utils.Utility.showProgress
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class FilmFragment : Fragment() {

    private lateinit var binding: FragmentFilmBinding
    private val mainViewModel: MainViewModel by viewModels()

    @Inject
    lateinit var filmAdapter: FilmAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilmBinding.inflate(
            layoutInflater,
            container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initObserver()

        val scheduler: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
        scheduler.scheduleAtFixedRate({
            // fetching movies everyday
            // random limit between 1 and 20. random page between 1 and 5
            binding.progressBar.showProgress()
            mainViewModel.getMovies((1..20).random(), (1..5).random())
        }, 0, 24, TimeUnit.HOURS)
    }

    private fun initUI() {
        binding.movieList.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
            adapter = filmAdapter
        }
    }

    private fun initObserver() {
        mainViewModel.movies.observe(this) { movies ->
            binding.progressBar.hideProgress()
            Timber.d("Movie size:: ${movies.size}")
            filmAdapter.apply {
                addMovies(movies)
                setClickListener(object : FilmAdapter.OnMovieItemClickListener {
                    override fun onMovieItemClick(movieId: Int) {
                        val bundle = bundleOf(KEY_FILM_ID to movieId)
                        findNavController().navigate(R.id.filmDetailFragment, bundle)
                    }
                })
                notifyDataSetChanged()
            }
        }
○
        mainViewModel.error.observe(this) { err ->
            binding.progressBar.hideProgress()
            Timber.e("MainActivity:: initObserver:: $err")
        }

    }

    private fun IntRange.random() =
        Random().nextInt((endInclusive + 1) - start) + start

}