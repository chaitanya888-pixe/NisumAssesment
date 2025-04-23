package com.sample.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.domain.model.UserInfo
import com.sample.domain.usecase.GetListUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val usersUseCase: GetListUsersUseCase) :
    ViewModel() {
    var isLoading by mutableStateOf(false)
    private val _usersList = MutableStateFlow<List<UserInfo>>(emptyList())
    var usersList = _usersList.asStateFlow()


    private val _selectedUser = MutableStateFlow<UserInfo?>(null)
    val selectedUser: StateFlow<UserInfo?> = _selectedUser

    fun setSelectedUser(user: UserInfo) {
        _selectedUser.value = user
    }

    fun fetchData(count: Int?) {
        isLoading = true

        viewModelScope.launch {
            usersUseCase.getListUsers(count.toString()).collectLatest { result ->
                result.onSuccess {
                    _usersList.value = it
                    isLoading = false

                }
                result.onFailure {
                    _usersList.value = emptyList()
                    isLoading = false

                }
            }
        }

    }



}