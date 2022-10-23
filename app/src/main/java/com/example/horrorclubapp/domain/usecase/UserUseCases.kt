package com.example.horrorclubapp.domain.usecase

data class UserUseCases(
    val getUser: GetUser,
    val insertUser: InsertUser,
    val signInWithGoogle: SignInWithGoogle,
    val signInWithCredentialGoogle: SignInWithCredentialGoogle
)
