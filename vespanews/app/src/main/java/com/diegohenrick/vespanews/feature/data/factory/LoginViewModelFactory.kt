package com.diegohenrick.vespanews.feature.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.diegohenrick.vespanews.feature.data.database.UserRoomDatabase
import com.diegohenrick.vespanews.feature.data.local.viewModel.LoginViewModel

class LoginViewModelFactory(private val database: UserRoomDatabase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(database) as T
        }
        throw IllegalArgumentException("ViewModel not found")
    }
}