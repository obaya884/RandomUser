package io.github.obaya884.randomuser.data

import io.github.obaya884.randomuser.domain.RandomUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserService {
    @GET("api/")
    suspend fun getRandomUsers(
        @Query("page") page: Int,
        @Query("results") pageSize: Int,
        @Query("gender") gender: String? = null
    ): Response<List<RandomUser>>
}