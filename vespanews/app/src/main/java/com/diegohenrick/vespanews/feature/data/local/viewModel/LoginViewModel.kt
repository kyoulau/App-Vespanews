package com.diegohenrick.vespanews.feature.data.local.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegohenrick.vespanews.feature.data.database.UserRoomDatabase
import com.diegohenrick.vespanews.feature.data.local.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val database: UserRoomDatabase) : ViewModel() {

    fun loginUser(email: String, password: String, callback: (Boolean, User?) -> Unit) {
        viewModelScope.launch {
            val user = withContext(Dispatchers.IO) {
                database.userDao().getUserByEmail(email)
            }

            if (user != null && user.password == password) {
                callback(true, user)
            } else {
                callback(false, null)
            }
        }
    }
}
