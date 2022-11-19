package io.github.obaya884.randomuser.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class DispatchersIOModule {
    @Provides
    fun provideDispatchersIO() = Dispatchers.IO
}