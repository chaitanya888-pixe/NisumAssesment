package com.sample.data.repository

import com.sample.data.mapper.toDomain
import com.sample.data.network.APIService
import com.sample.domain.model.UserInfo
import com.sample.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor (var apiService: APIService) : UsersRepository {
    override suspend fun getUsersList(count: String): Flow<Result<List<UserInfo>>> = flow {
        val response= apiService.getUsers(count)
        emit(Result.success(response.results.map {it.toDomain()  }))
    }.catch {
        e->emit(Result.failure(Exception(e.message)))
    }
}