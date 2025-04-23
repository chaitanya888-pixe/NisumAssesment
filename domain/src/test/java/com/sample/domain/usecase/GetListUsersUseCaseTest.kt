package com.sample.domain.usecase

import com.sample.domain.model.UserInfo
import com.sample.domain.repository.UsersRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetListUsersUseCaseTest {
    private lateinit var useCase: GetListUsersUseCase
    private val usersRepository: UsersRepository = mockk()

    @Before
    fun setUp() {
        useCase = GetListUsersUseCase(usersRepository)
    }

    @Test
    fun `getListUsers returns success result with list of users`() = runTest {
        val count = "2"
        val mockUsers = listOf(
            UserInfo(firstName = "John", lastName = "Doe", address = "USA", profilePicture = ""),
            UserInfo(firstName = "Jane", lastName = "Smith", address = "UK", profilePicture = "")
        )
        val expectedResult = Result.success(mockUsers)

        coEvery { usersRepository.getUsersList(count) } returns flowOf(expectedResult)

        val result = useCase.getListUsers(count).first()

        assertTrue(result.isSuccess)
        assertEquals(2, result.getOrNull()?.size)
        assertEquals("John", result.getOrNull()?.get(0)?.firstName)
    }

    @Test
    fun `getListUsers returns failure result`() = runTest {
        val count = "2"
        val exception = RuntimeException("Failed to fetch users")
        val expectedResult = Result.failure<List<UserInfo>>(exception)

        coEvery { usersRepository.getUsersList(count) } returns flowOf(expectedResult)

        val result = useCase.getListUsers(count).first()

        assertTrue(result.isFailure)
        assertEquals("Failed to fetch users", result.exceptionOrNull()?.message)
    }
}