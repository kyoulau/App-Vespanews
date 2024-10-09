package com.diegohenrick.vespanews.feature.data.local.viewModel

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.diegohenrick.vespanews.LoginActivity
import com.diegohenrick.vespanews.feature.data.database.UserRoomDatabase
import com.diegohenrick.vespanews.feature.data.local.dao.UserDao
import com.diegohenrick.vespanews.feature.data.local.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel(private val userDao: UserDao) : ViewModel() {
    fun registerUser(username: String, email: String, password: String, confirmPassword: String, onSuccess: () -> Unit,
                             onError: (String) -> Unit) {
        if (username.isEmpty() || email.isEmpty()) {
            onError("Please insert your email and password")
            return
        }

        if (password != confirmPassword) {
            onError("Passwords aren't matching")
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val existingUser = userDao.getUserByEmail(email)
                if (existingUser != null) {
                    withContext(Dispatchers.Main) {
                        onError("Email already registered!")
                    }
                } else {
                    val newUser = User(username = username, email = email, password = password)
                    userDao.insertUser(newUser)

                    withContext(Dispatchers.Main) {
                        onSuccess()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onError("Occurred an error: ${e.message}")
                }
            }
        }
    }
}