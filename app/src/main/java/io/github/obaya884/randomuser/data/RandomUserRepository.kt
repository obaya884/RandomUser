package io.github.obaya884.randomuser.data

import io.github.obaya884.randomuser.domain.RandomUsers
import retrofit2.Response

interface RandomUserRepository {
    suspend fun getRandomUsers(page: Int, pageSize: Int): Response<RandomUsers>
}