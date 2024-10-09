package com.diegohenrick.vespanews.feature.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.diegohenrick.vespanews.feature.data.local.dao.UserDao
import com.diegohenrick.vespanews.feature.data.local.viewModel.RegisterViewModel

class RegisterViewModelFactory(private val userDao: UserDao): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}