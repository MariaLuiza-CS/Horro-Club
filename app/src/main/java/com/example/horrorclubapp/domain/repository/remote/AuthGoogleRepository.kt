package com.example.horrorclubapp.domain.repository.remote

import com.example.horrorclubapp.domain.mode.Response
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.AuthCredential


typealias SignInWithGoogleResponse = Response<Boolean>
typealias SignInWithEmailAndPasswordFirebaseResponse = Response<Boolean>
typealias CreateUserWithEmailAndPasswordFirebaseResponse = Response<Boolean>
typealias SignInWithGoogleFirebaseResponse = Response<GoogleSignInClient>

interface AuthGoogleRepository {
    val isUserAuthenticatedInFirebase: Boolean

    suspend fun firebaseSignInWithGoogle(googleCredential: AuthCredential): SignInWithGoogleResponse

    suspend fun signInWithGoogleFirebaseResponse(): SignInWithGoogleFirebaseResponse

    suspend fun signInWithEmailAndPasswordFirebase(email: String, password: String): SignInWithEmailAndPasswordFirebaseResponse

    suspend fun createUserWithEmailAndPasswordFirebase(email: String, password: String): CreateUserWithEmailAndPasswordFirebaseResponse
}