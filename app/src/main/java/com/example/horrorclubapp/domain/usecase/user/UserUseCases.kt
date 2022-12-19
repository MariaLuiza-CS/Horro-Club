package com.example.horrorclubapp.domain.usecase.user

data class UserUseCases(
    val getUser: GetUser,
    val insertUser: InsertUser,
    val deleteUser: DeleteUser,
    val signInWithGoogle: SignInWithGoogle,
    val signInWithCredentialGoogle: SignInWithCredentialGoogle,
    val signInWithEmailAndPassword: SignInWithEmailAndPassword,
    val createWithEmailAndPassword: CreateWithEmailAndPassword
)
