package com.example.horrorclubapp.presentation.utils


import com.example.horrorclubapp.domain.mode.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RealDatabase {

    private var database: DatabaseReference = Firebase.database.reference

    fun insertNewUser(user: User) {
        database.child("users").child(user.id).setValue(user)
    }

}