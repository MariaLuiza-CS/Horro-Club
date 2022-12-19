package com.example.horrorclubapp.data.repository.remote

import com.example.horrorclubapp.domain.mode.Response
import com.example.horrorclubapp.domain.mode.User
import com.example.horrorclubapp.domain.repository.remote.*
import com.example.horrorclubapp.domain.usecase.user.UserUseCases
import com.example.horrorclubapp.presentation.utils.Constants.DISPLAY_NAME
import com.example.horrorclubapp.presentation.utils.Constants.EMAIL
import com.example.horrorclubapp.presentation.utils.Constants.GOOGLE_SIGNIN
import com.example.horrorclubapp.presentation.utils.Constants.ID
import com.example.horrorclubapp.presentation.utils.Constants.USERS
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
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
            isNewUser(authResult)
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

    override suspend fun signInWithEmailAndPasswordFirebase(
        email: String,
        password: String
    ): SignInWithEmailAndPasswordFirebaseResponse {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun createUserWithEmailAndPasswordFirebase(
        email: String,
        password: String
    ): CreateUserWithEmailAndPasswordFirebaseResponse {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            isNewUser(authResult)
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    private suspend fun isNewUser(authResult: AuthResult) {
        val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
        if (isNewUser) {
            addUserToDatabase()
        }
    }

    private suspend fun addUserToDatabase() {
        auth.currentUser?.apply {
            val user = toUser()
            db.child(USERS).child(uid).setValue(user).await()
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