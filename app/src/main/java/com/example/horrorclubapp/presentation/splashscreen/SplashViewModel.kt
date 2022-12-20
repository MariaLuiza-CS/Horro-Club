package com.example.horrorclubapp.presentation.splashscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horrorclubapp.data.onboarddatasource.DataStoreRepository
import com.example.horrorclubapp.domain.mode.Movie
import com.example.horrorclubapp.domain.mode.TVShow
import com.example.horrorclubapp.domain.repository.remote.AuthGoogleRepository
import com.example.horrorclubapp.domain.usecase.movie.MovieUseCases
import com.example.horrorclubapp.domain.usecase.tmbd.TMDBUseCases
import com.example.horrorclubapp.domain.usecase.tvshow.TVShowUseCases
import com.example.horrorclubapp.presentation.utils.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val authGoogleRepository: AuthGoogleRepository,
    private val movieUseCases: MovieUseCases,
    private val tvShowUseCases: TVShowUseCases,
    private val tMDBUseCases: TMDBUseCases
) : ViewModel() {
    private val apiKey = "b4d7a6e048cc30ff4e61996b602ee6e4"

    private val isUserAuthenticated get() = authGoogleRepository.isUserAuthenticatedInFirebase

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(Screen.Onboard.route)
    val startDestination: State<String> = _startDestination

    init {
        getAllMoviesFromApi()
        getAllTVShowFromApi()
        finishSplashScreen()
    }

    private fun finishSplashScreen() {
        viewModelScope.launch {
            dataStoreRepository.readOnBoardingState().collect { completed ->
                if (completed) {
                    if (isUserAuthenticated) _startDestination.value = Screen.Main.route
                    else _startDestination.value = Screen.Login.route
                } else {
                    _startDestination.value = Screen.Onboard.route
                }
            }
            _isLoading.value = false
        }
    }

    private fun getAllMoviesFromApi() =
        viewModelScope.launch {
            for (i in 1..200) {
                withContext(Dispatchers.Main) {
                    val response = tMDBUseCases.getMovieList.invoke(
                        apiKey, i
                    )
                    if (response.isSuccessful) {
                        response.body()?.let { movieList ->
                            movieList.results.forEach { movie ->
                                movie.genre_ids.forEach {
                                    if (it == 27) {
                                        movieUseCases.insertMovie.invoke(
                                            Movie(
                                                id = movie.id,
                                                title = movie.title,
                                                overview = movie.overview,
                                                poster_path = movie.poster_path,
                                                release_date = movie.release_date,
                                                genre_ids = 27,
                                                backdrop_path = movie.backdrop_path
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    private fun getAllTVShowFromApi() =
        viewModelScope.launch {
            for (i in 1..200) {
                withContext(Dispatchers.Main) {
                    val response = tMDBUseCases.getTVShowList.invoke(
                        apiKey, i
                    )
                    if (response.isSuccessful) {
                        response.body()?.let { tvShowList ->
                            tvShowList.results.forEach { tvShow ->
                                tvShow.genre_ids.forEach {
                                    if (it == 9648) {
                                        tvShowUseCases.insertTVShow.invoke(
                                            TVShow(
                                                id = tvShow.id,
                                                original_name = tvShow.original_name,
                                                overview = tvShow.overview,
                                                poster_path = tvShow.poster_path,
                                                first_air_date = tvShow.first_air_date,
                                                genre_ids = 9648,
                                                backdrop_path = tvShow.backdrop_path
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
}
