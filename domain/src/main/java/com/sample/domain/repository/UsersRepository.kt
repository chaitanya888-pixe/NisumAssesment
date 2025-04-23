package com.sample.domain.repository

import com.sample.domain.model.UserInfo
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun getUsersList(count: String):Flow<Result<List<UserInfo>>>
}