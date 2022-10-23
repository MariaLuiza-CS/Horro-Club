package com.example.horrorclubapp.presentation.loginscreen

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

@Composable
fun rememberFirebaseAuthLauncher(
    viewModel: LoginScreenViewModel = hiltViewModel(),
    onAuthComplete: (Boolean) -> Unit,
    onAuthError: (ApiException) -> Unit
): ManagedActivityResultLauncher<Intent, ActivityResult> {
    val scope = rememberCoroutineScope()
    return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        if (signInAccountTask.isSuccessful) {
            try {
                val googleSignInAccount = signInAccountTask.getResult(ApiException::class.java)
                if (googleSignInAccount != null) {
                    val authCredential =
                        GoogleAuthProvider.getCredential(googleSignInAccount.idToken, null)
                    scope.launch {
                        viewModel.signInWithGoogle(authCredential)
                        onAuthComplete(true)
                    }
                }
            } catch (e: ApiException) {
                onAuthError(e)
            }
        }
    }
}