package com.betterafter.template.presentation.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betterafter.template.domain.core.DataState
import com.betterafter.template.domain.user.entity.User
import com.betterafter.template.domain.user.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<DataState<User>>(DataState.Initial)
    val uiState: StateFlow<DataState<User>> = _uiState.asStateFlow()

    init {
        loadUser("123")
    }

    fun loadUser(userId: String) {
        viewModelScope.launch {
            _uiState.value = DataState.Loading()
            _uiState.value = getUserUseCase(userId)
        }
    }
}
