package io.github.obaya884.randomuser.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.obaya884.randomuser.domain.usecase.RandomUserInteractor
import io.github.obaya884.randomuser.domain.usecase.RandomUserUseCase

@Module
@InstallIn(SingletonComponent::class)
abstract class RandomUserUseCaseModule {
    @Binds
    abstract fun provideRandomUserUseCase(interactor: RandomUserInteractor): RandomUserUseCase
}