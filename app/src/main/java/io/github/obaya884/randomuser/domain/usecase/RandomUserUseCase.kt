package io.github.obaya884.randomuser.domain.usecase

import io.github.obaya884.randomuser.domain.RandomUser
import kotlinx.coroutines.flow.Flow

interface RandomUserUseCase {
    suspend fun getRandomUsers(page: Int, pageSize: Int): Flow<List<RandomUser>>
}