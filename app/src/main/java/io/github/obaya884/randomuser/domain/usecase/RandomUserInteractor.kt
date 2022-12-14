package io.github.obaya884.randomuser.domain.usecase

import io.github.obaya884.randomuser.data.RandomUserRepository
import io.github.obaya884.randomuser.domain.RandomUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RandomUserInteractor @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val repository: RandomUserRepository
) : RandomUserUseCase {
    override suspend fun getRandomUsers(
        page: Int,
        pageSize: Int
    ): Flow<List<RandomUser>> = flow {
        val users = repository.getRandomUsers(page, pageSize).body()?.results ?: listOf()
        emit(users)
    }.flowOn(dispatcher)
}