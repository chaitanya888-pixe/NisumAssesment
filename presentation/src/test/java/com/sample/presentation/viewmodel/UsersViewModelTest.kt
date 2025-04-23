package com.sample.presentation.viewmodel

import com.sample.domain.model.UserInfo
import com.sample.domain.usecase.GetListUsersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class UsersViewModelTest {
    private lateinit var viewModel: UsersViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var mockUsersUseCase: GetListUsersUseCase


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
        viewModel = UsersViewModel(mockUsersUseCase)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchData should update usersList and isLoading on success`() = runTest {
        val users = listOf(UserInfo("John", "Doe", "123 Street", "pic_url"))
        val resultFlow = flow {
            emit(Result.success(users))
        }
        `when`(mockUsersUseCase.getListUsers("5")).thenReturn(resultFlow)
        viewModel.fetchData(5)
        advanceUntilIdle()

        assertEquals(false, viewModel.isLoading)
        assertEquals(users, viewModel.usersList.value)
    }

    @Test
    fun `fetchData should set empty list and isLoading false on failure`() = runTest {
        val resultFlow = flow {
            emit(Result.failure<List<UserInfo>>(Throwable("Error")))
        }
        `when`(mockUsersUseCase.getListUsers("10")).thenReturn(resultFlow)

        viewModel.fetchData(10)
        advanceUntilIdle()

        // Then
        assertEquals(false, viewModel.isLoading)
        assertEquals(emptyList<UserInfo>(), viewModel.usersList.value)
    }

    @Test
    fun `setSelectedUser should update selectedUser`() = runTest {
        val user = UserInfo("Alice", "Smith", "456 Lane", "pic_url")

        viewModel.setSelectedUser(user)

        assertEquals(user, viewModel.selectedUser.value)
    }
}