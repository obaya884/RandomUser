package io.github.obaya884.randomuser.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.obaya884.randomuser.data.RandomUserApiRepository
import io.github.obaya884.randomuser.data.RandomUserRepository

@InstallIn(SingletonComponent::class)
@Module
abstract class RandomUserRepositoryModule {
    @Binds
    abstract fun bindRandomUserRepository(
        repository: RandomUserApiRepository
    ): RandomUserRepository
}