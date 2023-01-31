package com.example.cleanarchslvglass.presentation.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.cleanarchslvglass.presentation.viewmodel.AuthViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.cleanarchslvglass.databinding.ActivitySignUpBinding
import com.example.cleanarchslvglass.domain.models.User
import kotlinx.coroutines.*


class SignUpActivity : AppCompatActivity() {

    private lateinit var viewModel: AuthViewModel
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]
    }

    override fun onResume() {
        super.onResume()

        val firstName = binding.firstNameUp.text
        val lastName = binding.lastNameUp.text
        val email = binding.emailUp.text
        val password = binding.passwordUp.text
        val confirmPassword = binding.confirmPasswordUp.text
        val signUpButton = binding.signUp
        val alreadyHave = binding.alreadyHave

        alreadyHave.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        signUpButton.setOnClickListener {
            if (
                firstName!!.isNotEmpty()
                && lastName!!.isNotEmpty()
                && email!!.isNotEmpty()
                && password!!.isNotEmpty()
                && confirmPassword!!.isNotEmpty()
            ){

                if (password.toString() == confirmPassword.toString()){
                    val user: User = User(firstName = firstName.toString(), lastName = lastName.toString(), email = email.toString())

                    var result = ""
                    val signUp = lifecycleScope.launch {
                        viewModel.createUser(user, password.toString())
                        result = viewModel.createUser(user, password.toString())
                    }

                    if (signUp.isCompleted){
                        Toast.makeText(this@SignUpActivity, result, Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, SignInActivity::class.java)
                        startActivity(intent)
                    } else if (signUp.isCancelled){
                        lifecycleScope.launch {
                            signUp.cancelAndJoin()
                        }
                        Toast.makeText(this@SignUpActivity, result, Toast.LENGTH_SHORT).show()
                    }

                } else {
                    Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Все поля должны быть заполнены", Toast.LENGTH_SHORT).show()
            }
        }
    }
}