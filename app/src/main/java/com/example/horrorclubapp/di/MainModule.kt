package com.example.horrorclubapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.horrorclubapp.data.local.onboarddatasource.DataStoreRepository
import com.example.horrorclubapp.data.local.usedatasource.UserDataBase
import com.example.horrorclubapp.data.repository.UserRepositoryImpl
import com.example.horrorclubapp.domain.repository.UserRepository
import com.example.horrorclubapp.domain.usecase.GetUser
import com.example.horrorclubapp.domain.usecase.InsertUser
import com.example.horrorclubapp.domain.usecase.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

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
    fun provideUserUseCases(repository: UserRepository): UserUseCases {
        return UserUseCases(
            getUser = GetUser(repository),
            insertUser = InsertUser(repository)
        )
    }

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ) = DataStoreRepository(context = context)

}