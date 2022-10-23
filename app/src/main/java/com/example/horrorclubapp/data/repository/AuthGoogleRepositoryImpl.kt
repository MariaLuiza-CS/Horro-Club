package com.example.horrorclubapp.data.repository

import com.example.horrorclubapp.domain.mode.Response
import com.example.horrorclubapp.domain.mode.User
import com.example.horrorclubapp.domain.repository.AuthGoogleRepository
import com.example.horrorclubapp.domain.repository.SignInWithGoogleFirebaseResponse
import com.example.horrorclubapp.domain.repository.SignInWithGoogleResponse
import com.example.horrorclubapp.domain.usecase.UserUseCases
import com.example.horrorclubapp.presentation.utils.Constants.DISPLAY_NAME
import com.example.horrorclubapp.presentation.utils.Constants.EMAIL
import com.example.horrorclubapp.presentation.utils.Constants.GOOGLE_SIGNIN
import com.example.horrorclubapp.presentation.utils.Constants.ID
import com.example.horrorclubapp.presentation.utils.Constants.USERS
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class AuthGoogleRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    @Named(GOOGLE_SIGNIN)
    private var googleSignIn: GoogleSignInClient,
    private val db: DatabaseReference
) : AuthGoogleRepository {
    override val isUserAuthenticatedInFirebase: Boolean =
        auth.currentUser != null

    override suspend fun firebaseSignInWithGoogle(googleCredential: AuthCredential): SignInWithGoogleResponse {
        return try {
            val authResult = auth.signInWithCredential(googleCredential).await()
            val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
            if (isNewUser) {
                addUserToDatabase()
            }
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun signInWithGoogleFirebaseResponse(): SignInWithGoogleFirebaseResponse {
        return try {
            Response.Success(googleSignIn)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    private suspend fun addUserToDatabase() {
        auth.currentUser?.apply {
            val user = toUser()
            db.child(USERS).child(uid).setValue(user).await()
//            userUseCases.insertUser.invoke(
//                User(
//                    uid,
//                    displayName,
//                    email
//                )
//            )
        }
    }
}

fun FirebaseUser.toUser(): Map<out Any, String?> {
    return mapOf(
        ID to uid,
        DISPLAY_NAME to displayName,
        EMAIL to email
    )
}