package com.example.horrorclubapp.data.repository.remote

import com.example.horrorclubapp.domain.mode.Response
import com.example.horrorclubapp.domain.mode.User
import com.example.horrorclubapp.domain.repository.remote.ProfileRepository
import com.example.horrorclubapp.domain.repository.remote.RevokeAccessResponse
import com.example.horrorclubapp.domain.repository.remote.SignOutResponse
import com.example.horrorclubapp.domain.usecase.user.UserUseCases
import com.example.horrorclubapp.presentation.utils.Constants.USERS
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: DatabaseReference
) : ProfileRepository {
    override val displayName: String = auth.currentUser?.displayName.toString()

    override suspend fun authUser(): User {
        return User(
            id = auth.currentUser?.uid.toString(),
            email = auth.currentUser?.email,
            name = auth.currentUser?.displayName
        )
    }

    override suspend fun signOut(): SignOutResponse {
        return try {
            auth.signOut()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun revokeAccess(): RevokeAccessResponse {
        return try {
            auth.currentUser?.apply {
                db.child(USERS).child(uid).removeValue().await()
                delete().await()
            }
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }
}
