package com.diegohenrick.vespanews


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.diegohenrick.vespanews.feature.data.database.UserRoomDatabase
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var goToSignUpButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        goToSignUpButton = findViewById(R.id.goToSignUpButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            } else {
                Toast.makeText(this, "Por favor insira o email e a senha", Toast.LENGTH_SHORT).show()
            }
        }

        goToSignUpButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(email: String, password: String) {
        lifecycleScope.launch {
            val userDao = UserRoomDatabase.getDatabase(this@LoginActivity).userDao()
            val user = userDao.getUserByEmail(email)

            if (user != null && user.password == password) {
                Toast.makeText(this@LoginActivity, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this@LoginActivity, "Email ou senha inválidos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


