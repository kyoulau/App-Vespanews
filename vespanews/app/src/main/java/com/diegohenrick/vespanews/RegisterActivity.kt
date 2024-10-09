package com.diegohenrick.vespanews

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.diegohenrick.vespanews.feature.data.database.UserRoomDatabase
import com.diegohenrick.vespanews.feature.data.local.dao.UserDao
import com.diegohenrick.vespanews.feature.data.local.entity.User
import com.diegohenrick.vespanews.feature.data.local.viewModel.RegisterViewModel
import com.diegohenrick.vespanews.feature.data.factory.RegisterViewModelFactory

import kotlinx.coroutines.launch

class RegisterActivity :AppCompatActivity() {

    private lateinit var userDao: UserDao
    private lateinit var db: UserRoomDatabase
    private lateinit var usernameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText

    private val registerViewModel: RegisterViewModel by viewModels {
        RegisterViewModelFactory(UserRoomDatabase.getDatabase(this).userDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        emailEditText = findViewById<EditText>(R.id.emailEditText)
        passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        confirmPasswordEditText = findViewById<EditText>(R.id.confirmPasswordEditText)

        db = UserRoomDatabase.getDatabase(this)
        userDao = db.userDao()

        val registerButton = findViewById<Button>(R.id.registerButton)
        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()


            registerViewModel.registerUser(
                username = username,
                email = email,
                password = password,
                confirmPassword = confirmPassword,
                onSuccess = {
                    Toast.makeText(this, "User registered successfully!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                },
                onError = { errorMessage ->
                    Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                }
            )        }

    }



}