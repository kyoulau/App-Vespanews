package com.diegohenrick.vespanews

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.diegohenrick.vespanews.feature.data.database.UserRoomDatabase
import com.diegohenrick.vespanews.feature.data.local.dao.UserDao
import com.diegohenrick.vespanews.feature.data.local.entity.User
import kotlinx.coroutines.launch

class RegisterActivity :AppCompatActivity() {

    private lateinit var userDao: UserDao
    private lateinit var db: UserRoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        db = UserRoomDatabase.getDatabase(this)
        userDao = db.userDao()

        val registerButton = findViewById<Button>(R.id.registerButton)
        registerButton.setOnClickListener {
            registerUser()
        }

    }

    private fun registerUser() {
        val username = findViewById<EditText>(R.id.usernameEditText).text.toString()
        val email = findViewById<EditText>(R.id.emailEditText).text.toString()
        val password = findViewById<EditText>(R.id.passwordEditText).text.toString()
        val confirmPassword = findViewById<EditText>(R.id.confirmPasswordEditText).text.toString()
        if (username.isNotEmpty() && email.isNotEmpty()) {

            if (password == confirmPassword) {
                lifecycleScope.launch {
                    val existingUser = userDao.getUserByEmail(email)

                    if (existingUser == null) {
                        val newUser = User(username = username, email = email, password = password)
                        userDao.insertUser(newUser)
                        Toast.makeText(
                            this@RegisterActivity,
                            "Usuário registrado com sucesso!",
                            Toast.LENGTH_SHORT
                        ).show()

                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        startActivity(intent)

                        finish()

                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Email já registrado!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            } else {
                Toast.makeText(this, "As senhas não coincidem", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Por favor insira o email e a senha", Toast.LENGTH_SHORT).show()
        }
    }

}