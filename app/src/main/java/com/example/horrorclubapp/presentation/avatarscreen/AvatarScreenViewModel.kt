package com.example.horrorclubapp.presentation.avatarscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.horrorclubapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AvatarScreenViewModel @Inject constructor() : ViewModel() {
    var charactersList by mutableStateOf(
        listOf(
            R.drawable.ic_zombie_girl,
            R.drawable.ic_zombie_boy,
            R.drawable.ic_glasses_person,
            R.drawable.ic_buss_person,
            R.drawable.ic_cut_person,
            R.drawable.ic_pink_person,
            R.drawable.ic_punk_person,
            R.drawable.ic_simple_person,
            R.drawable.ic_long_hair_person,
            R.drawable.ic_simple_glasses_person
        )
    )
        private set
}