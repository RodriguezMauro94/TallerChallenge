package com.maurosergiorodriguez.tallerchallenge.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maurosergiorodriguez.tallerchallenge.domain.LoginUseCase
import com.maurosergiorodriguez.tallerchallenge.ui.model.LoginModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    val loginUseCase: LoginUseCase = LoginUseCase()
): ViewModel() {
    var loginUiState = mutableStateOf<LoginUiState>(value = LoginUiState.Offline())

    fun doLogin(user: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            //TODO manejo de errores
            loginUiState.value = LoginUiState.Loading()
            val result = loginUseCase.doLogin(user, password)
            loginUiState.value = LoginUiState.Success(result)
        }
    }
}

sealed interface LoginUiState {
    class Loading: LoginUiState
    class Offline: LoginUiState
    data class Error(
        val errorDescription: String
    ): LoginUiState
    class Success(
        val loginData: LoginModel
    ) : LoginUiState
}