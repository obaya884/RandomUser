package io.github.obaya884.randomuser.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.obaya884.randomuser.domain.RandomUserInteractor
import io.github.obaya884.randomuser.domain.RandomUserUseCase

@Module
@InstallIn(SingletonComponent::class)
abstract class RandomUserUseCaseModule {
    @Binds
    abstract fun provideRandomUserUseCase(interactor: RandomUserInteractor): RandomUserUseCase
}