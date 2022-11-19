package io.github.obaya884.randomuser.data

import io.github.obaya884.randomuser.domain.RandomUser
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RandomUserRepository {
    suspend fun getRandomUsers(page: Int, pageSize: Int): Flow<Response<List<RandomUser>>>
}