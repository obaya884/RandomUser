package io.github.obaya884.randomuser.data

import io.github.obaya884.randomuser.domain.RandomUsers
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RandomUserApiRepository @Inject constructor(
    private val randomUserService: RandomUserService
) : RandomUserRepository {
    override suspend fun getRandomUsers(
        page: Int,
        pageSize: Int
    ): Response<RandomUsers> =
        randomUserService.getRandomUsers(page, pageSize)
}