package io.github.obaya884.randomuser.domain

import io.github.obaya884.randomuser.data.RandomUserRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class RandomUserInteractor @Inject constructor(
    private val repository: RandomUserRepository
): RandomUserUseCase {
    override suspend fun getRandomUsers(
        page: Int,
        pageSize: Int
    ): Flow<Response<List<RandomUser>>> {
        return repository.getRandomUsers(page, pageSize)
    }
}