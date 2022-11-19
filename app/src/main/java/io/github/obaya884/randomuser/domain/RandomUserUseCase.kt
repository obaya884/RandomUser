package io.github.obaya884.randomuser.domain

import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RandomUserUseCase {
    suspend fun getRandomUsers(page: Int, pageSize: Int): Flow<Response<List<RandomUser>>>
}