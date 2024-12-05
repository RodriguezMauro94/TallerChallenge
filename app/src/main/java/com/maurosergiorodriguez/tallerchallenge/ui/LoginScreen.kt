package com.maurosergiorodriguez.tallerchallenge.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.Lifecycle

@Composable
fun LoginScreen(loginViewModel: LoginViewModel) {
    val uiState = loginViewModel.loginUiState.value

    when(uiState) {
        is LoginUiState.Offline -> {
            LoginForm(loginViewModel)
        }
        is LoginUiState.Loading -> {
            CircularProgressIndicator()
        }
        is LoginUiState.Error -> {
            val error = loginViewModel.loginUiState.value as LoginUiState.Error
            Text(error.errorDescription)
        }
        is LoginUiState.Success -> {
            val loginData = loginViewModel.loginUiState.value as LoginUiState.Success
            Text(
                loginData.loginData.userFullName
            )
        }
    }
}

@Composable
fun LoginForm(loginViewModel: LoginViewModel) {
    var user = rememberSaveable {
        mutableStateOf("")
    }
    var password = rememberSaveable {
        mutableStateOf("")
    }
    Column {
        TextField(
            value = user.value,
            placeholder = {
                Text("User")
            },
            onValueChange = {
                user.value = it
            }
        )
        TextField(
            value = password.value,
            placeholder = {
                Text("Password")
            },
            onValueChange = {
                password.value = it
            }
        )

        Button(
            onClick = {
                loginViewModel.doLogin(user.value, password.value)
            }
        ) {
            Text("Login")
        }
    }
}
