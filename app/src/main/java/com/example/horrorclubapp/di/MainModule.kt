package com.example.horrorclubapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.horrorclubapp.R
import com.example.horrorclubapp.data.UserDataBase
import com.example.horrorclubapp.data.onboarddatasource.DataStoreRepository
import com.example.horrorclubapp.data.repository.local.MovieRepositoryImpl
import com.example.horrorclubapp.data.repository.local.TVShowRepositoryImpl
import com.example.horrorclubapp.data.repository.local.UserRepositoryImpl
import com.example.horrorclubapp.data.repository.remote.AuthGoogleRepositoryImpl
import com.example.horrorclubapp.data.repository.remote.ProfileRepositoryImpl
import com.example.horrorclubapp.data.repository.remote.TheMovieDbRepositoryImpl
import com.example.horrorclubapp.domain.repository.local.MovieRepository
import com.example.horrorclubapp.domain.repository.local.TVShowRepository
import com.example.horrorclubapp.domain.repository.local.UserRepository
import com.example.horrorclubapp.domain.repository.remote.AuthGoogleRepository
import com.example.horrorclubapp.domain.repository.remote.ProfileRepository
import com.example.horrorclubapp.domain.repository.remote.TheMovieDbRepository
import com.example.horrorclubapp.domain.usecase.movie.GetMovies
import com.example.horrorclubapp.domain.usecase.movie.InsertMovie
import com.example.horrorclubapp.domain.usecase.movie.MovieUseCases
import com.example.horrorclubapp.domain.usecase.tmbd.GetMovieList
import com.example.horrorclubapp.domain.usecase.tmbd.GetTVShowList
import com.example.horrorclubapp.domain.usecase.tmbd.TMDBUseCases
import com.example.horrorclubapp.domain.usecase.tvshow.GetTVShow
import com.example.horrorclubapp.domain.usecase.tvshow.InsertTVShow
import com.example.horrorclubapp.domain.usecase.tvshow.TVShowUseCases
import com.example.horrorclubapp.domain.usecase.user.*
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
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserRepository(db: UserDataBase): UserRepository {
        return UserRepositoryImpl(db.userDatabaseDao)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(db: UserDataBase): MovieRepository {
        return MovieRepositoryImpl(db.movieDatabaseDao)
    }

    @Provides
    @Singleton
    fun provideTVShowRepository(db: UserDataBase): TVShowRepository {
        return TVShowRepositoryImpl(db.tvShowDao)
    }

    @Provides
    @Singleton
    fun provideTheMovieRepository(): TheMovieDbRepository {
        return TheMovieDbRepositoryImpl()
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
            deleteUser = DeleteUser(userRepository),
            signInWithGoogle = SignInWithGoogle(authGoogleRepository),
            signInWithCredentialGoogle = SignInWithCredentialGoogle(authGoogleRepository),
            signInWithEmailAndPassword = SignInWithEmailAndPassword(authGoogleRepository),
            createWithEmailAndPassword = CreateWithEmailAndPassword(authGoogleRepository)
        )
    }

    @Provides
    @Singleton
    fun provideMovieUseCases(
        movieRepository: MovieRepository
    ): MovieUseCases {
        return MovieUseCases(
            getMovies = GetMovies(movieRepository),
            insertMovie = InsertMovie(movieRepository)
        )
    }

    @Provides
    @Singleton
    fun provideTVShowUseCases(
        tvShowRepository: TVShowRepository
    ): TVShowUseCases {
        return TVShowUseCases(
            getTVShow = GetTVShow(tvShowRepository),
            insertTVShow = InsertTVShow(tvShowRepository)
        )
    }

    @Provides
    @Singleton
    fun provideTMDBUseCases(
        theMovieDbRepository: TheMovieDbRepository
    ): TMDBUseCases {
        return TMDBUseCases(
            getMovieList = GetMovieList(theMovieDbRepository),
            getTVShowList = GetTVShowList(theMovieDbRepository)
        )
    }

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ) = DataStoreRepository(context = context)
}
