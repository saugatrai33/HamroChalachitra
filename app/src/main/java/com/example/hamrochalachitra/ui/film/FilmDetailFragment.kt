package com.example.hamrochalachitra.ui.film

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hamrochalachitra.databinding.FragmentDetailFilmBinding
import com.example.hamrochalachitra.ui.MainViewModel
import com.example.hamrochalachitra.utils.Constants
import com.example.hamrochalachitra.utils.Utility.hideProgress
import com.example.hamrochalachitra.utils.Utility.showProgress
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.NavHostFragment
import com.example.hamrochalachitra.R

@AndroidEntryPoint
class FilmDetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailFilmBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailFilmBinding.inflate(
            layoutInflater,
            container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId = arguments?.getInt(Constants.KEY_FILM_ID)
        initObserver()
        binding.progressBar.showProgress()
        mainViewModel.getMovieDetail(movieId = movieId!!)
    }

    private fun initObserver() {
        mainViewModel.movie.observe(this) { movie ->
            binding.progressBar.hideProgress()
            val uri: Uri = Uri.parse(movie.backgroundImage)
            binding.movieImg.setImageURI(uri)
            binding.title.text = movie.title
            binding.rating.text = movie.rating.toString()
            binding.genre.text = movie.genres.toString()
            binding.descMovie.text = movie.descriptionFull
        }

        mainViewModel.error.observe(this) { err ->
            binding.progressBar.hideProgress()
            Timber.e("MovieDetailActivity:: initObserver: $err")
        }
    }

}