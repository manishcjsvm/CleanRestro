package com.assignment.data.di

import com.assignment.data.repository.DisneyRepositoryImpl
import com.assignment.domain.repository.DisneyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(disneyRepositoryImpl: DisneyRepositoryImpl): DisneyRepository
}