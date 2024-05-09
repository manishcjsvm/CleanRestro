package com.assignment.domain.di

import com.assignment.domain.usecases.GetDisneyCharacterDetailsUseCase
import com.assignment.domain.usecases.GetDisneyCharacterDetailsUseCaseImpl
import com.assignment.domain.usecases.GetDisneyCharactersListUseCase
import com.assignment.domain.usecases.GetDisneyCharactersListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindDisneyCharactersListUseCase(getDisneyCharactersListUseCaseImpl: GetDisneyCharactersListUseCaseImpl): GetDisneyCharactersListUseCase

    @Binds
    abstract fun bindDisneyCharacterDetailsUseCase(getDisneyCharacterDetailsUseCaseImpl: GetDisneyCharacterDetailsUseCaseImpl): GetDisneyCharacterDetailsUseCase
}