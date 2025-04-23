package com.sample.domain.usecase

import com.sample.domain.model.UserInfo
import com.sample.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetListUsersUseCase @Inject constructor(var users: UsersRepository) {
    suspend fun getListUsers(count: String): Flow<Result<List<UserInfo>>> {
        return users.getUsersList(count)
    }
}