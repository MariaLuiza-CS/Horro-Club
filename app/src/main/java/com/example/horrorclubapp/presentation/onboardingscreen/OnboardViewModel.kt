package com.example.horrorclubapp.presentation.onboardingscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horrorclubapp.data.local.onboarddatasource.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveOnBoardingState(completed = completed)
        }
    }
}