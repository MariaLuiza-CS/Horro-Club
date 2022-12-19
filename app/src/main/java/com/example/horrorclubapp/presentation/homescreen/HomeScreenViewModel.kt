package com.example.horrorclubapp.presentation.homescreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horrorclubapp.domain.mode.Movie
import com.example.horrorclubapp.domain.mode.MovieResponse
import com.example.horrorclubapp.domain.mode.Response
import com.example.horrorclubapp.domain.mode.TVShow
import com.example.horrorclubapp.domain.repository.remote.ProfileRepository
import com.example.horrorclubapp.domain.repository.remote.RevokeAccessResponse
import com.example.horrorclubapp.domain.repository.remote.SignOutResponse
import com.example.horrorclubapp.domain.usecase.movie.MovieUseCases
import com.example.horrorclubapp.domain.usecase.tvshow.TVShowUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val movieUseCases: MovieUseCases,
    private val  tvShowUseCases: TVShowUseCases
) : ViewModel() {

    val displayName get() = profileRepository.displayName

    var movieResponses by mutableStateOf<List<Movie>?>(
        listOf(
            Movie(
                123,
                "title",
                "overview",
                "post",
                "date",
                "backdrop_path",
                27
            )
        )
    )
        private set

    var tvShowResponses by mutableStateOf<List<TVShow>?>(
        listOf(
            TVShow(
                123,
                "title",
                "overview",
                "post",
                "date",
                "backdrop_path",
                27
            )
        )
    )
        private set

    var signOutResponse by mutableStateOf<SignOutResponse>(Response.Success(false))
        private set

    var revokeAccessResponse by mutableStateOf<RevokeAccessResponse>(Response.Success(false))
        private set

    init {
        getAllMovies()
        getAllTVShow()
    }

    fun signOut() = viewModelScope.launch {
        signOutResponse = Response.Loading
        signOutResponse = profileRepository.signOut()
    }

    fun revokeAccess() = viewModelScope.launch {
        revokeAccessResponse = Response.Loading
        revokeAccessResponse = profileRepository.revokeAccess()
    }

    fun getAllMovies() = viewModelScope.launch {
        movieResponses = movieUseCases.getMovies.invoke()
    }

    fun getAllTVShow() = viewModelScope.launch {
        tvShowResponses = tvShowUseCases.getTVShow.invoke()
    }
}
