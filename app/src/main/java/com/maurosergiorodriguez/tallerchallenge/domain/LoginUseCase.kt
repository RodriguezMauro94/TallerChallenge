package com.maurosergiorodriguez.tallerchallenge.domain

import com.maurosergiorodriguez.tallerchallenge.ui.model.LoginModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginUseCase {
    suspend fun doLogin(user: String, password: String): LoginModel {
        withContext(Dispatchers.IO) {
            Thread.sleep(5000)
        }

        return LoginModel(
            user = user,
            password = password,
            userFullName = user + password
        )
    }
}