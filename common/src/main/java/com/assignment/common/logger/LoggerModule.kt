package com.assignment.common.logger

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LoggerModule {

    @Binds
    abstract fun bindLogger(loggerImpl: LoggerImpl): Logger
}