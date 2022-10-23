package com.example.horrorclubapp.domain.repository

import com.example.horrorclubapp.domain.mode.Response
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.AuthCredential


typealias SignInWithGoogleResponse = Response<Boolean>
typealias SignInWithGoogleFirebaseResponse = Response<GoogleSignInClient>

interface AuthGoogleRepository {
    val isUserAuthenticatedInFirebase: Boolean

    suspend fun firebaseSignInWithGoogle(googleCredential: AuthCredential): SignInWithGoogleResponse

    suspend fun signInWithGoogleFirebaseResponse(): SignInWithGoogleFirebaseResponse
}