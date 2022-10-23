package com.example.horrorclubapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.horrorclubapp.R
import com.example.horrorclubapp.data.local.onboarddatasource.DataStoreRepository
import com.example.horrorclubapp.data.local.usedatasource.UserDataBase
import com.example.horrorclubapp.data.repository.AuthGoogleRepositoryImpl
import com.example.horrorclubapp.data.repository.ProfileRepositoryImpl
import com.example.horrorclubapp.data.repository.UserRepositoryImpl
import com.example.horrorclubapp.domain.repository.AuthGoogleRepository
import com.example.horrorclubapp.domain.repository.ProfileRepository
import com.example.horrorclubapp.domain.repository.UserRepository
import com.example.horrorclubapp.domain.usecase.*
import com.example.horrorclubapp.presentation.utils.Constants.GOOGLE_SIGNIN
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    fun provideContext(
        app: Application
    ): Context = app.applicationContext

    @Provides
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    fun provideDatabaseReference() = Firebase.database.reference

    @Provides
    fun provideGoogleSignInOptions(
        app: Application
    ) = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(app.getString(R.string.web_client_id))
        .requestEmail()
        .build()

    @Provides
    @Named(GOOGLE_SIGNIN)
    fun provideGoogleSignInClient(
        app: Application,
        options: GoogleSignInOptions
    ) = GoogleSignIn.getClient(app, options)

    @Provides
    fun provideAuthGoogleRepository(
        auth: FirebaseAuth,
        @Named(GOOGLE_SIGNIN)
        googleSignIn: GoogleSignInClient,
        db: DatabaseReference
    ): AuthGoogleRepository = AuthGoogleRepositoryImpl(
        auth = auth,
        googleSignIn = googleSignIn,
        db = db
    )

    @Provides
    fun provideProfileRepository(
        auth: FirebaseAuth,
        db: DatabaseReference
    ): ProfileRepository = ProfileRepositoryImpl(
        auth = auth,
        db = db
    )

    @Provides
    @Singleton
    fun provideUserDatabase(app: Application): UserDataBase {
        return Room
            .databaseBuilder(
                app,
                UserDataBase::class.java,
                UserDataBase.DATABASE_NAME
            )
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserRepository(db: UserDataBase): UserRepository {
        return UserRepositoryImpl(db.userDatabaseDao)
    }

    @Provides
    @Singleton
    fun provideUserUseCases(
        userRepository: UserRepository,
        authGoogleRepository: AuthGoogleRepository
    ): UserUseCases {
        return UserUseCases(
            getUser = GetUser(userRepository),
            insertUser = InsertUser(userRepository),
            signInWithGoogle = SignInWithGoogle(authGoogleRepository),
            signInWithCredentialGoogle = SignInWithCredentialGoogle(authGoogleRepository)
        )
    }

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ) = DataStoreRepository(context = context)

}