package com.example.horrorclubapp.domain.repository.remote

import com.example.horrorclubapp.domain.mode.Response
import com.example.horrorclubapp.domain.mode.User

typealias SignOutResponse = Response<Boolean>
typealias RevokeAccessResponse = Response<Boolean>

interface ProfileRepository {
    val displayName: String

    suspend fun authUser(): User

    suspend fun signOut(): SignOutResponse

    suspend fun revokeAccess(): RevokeAccessResponse
}