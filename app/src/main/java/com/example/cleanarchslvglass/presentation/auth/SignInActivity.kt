package com.example.cleanarchslvglass.presentation.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.cleanarchslvglass.R
import com.example.cleanarchslvglass.databinding.ActivitySignInBinding
import com.example.cleanarchslvglass.databinding.ActivitySignUpBinding
import com.example.cleanarchslvglass.domain.models.User
import com.example.cleanarchslvglass.presentation.MainActivity
import com.example.cleanarchslvglass.presentation.viewmodel.AuthViewModel
import kotlinx.coroutines.*

class SignInActivity : AppCompatActivity() {

    private lateinit var viewModel: AuthViewModel
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]
    }

    override fun onResume() {
        super.onResume()

        val email = binding.emailIn.text
        val password = binding.passwordIn.text
        val notYet = binding.NotYet
        val signInButton = binding.signIn

        notYet.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        signInButton.setOnClickListener {
            if (
                email!!.isNotEmpty()
                && password!!.isNotEmpty()
            ){
                val user = User(email = email.toString())
                var result = ""
                val signIn = lifecycleScope.launch {
                    try {
                        viewModel.authUser(user, password.toString())
                        result = viewModel.authUser(user, password.toString())
                    }
                    catch (e: CancellationException){
                        startActivity(this@SignInActivity.intent)
                        Toast.makeText(this@SignInActivity, e.toString(), Toast.LENGTH_SHORT).show()
                    }
                    finally {

                    }
                }

                if (signIn.isCompleted){
                    Toast.makeText(this@SignInActivity, result, Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else if (signIn.isCancelled){
                    startActivity(this.intent)
                    Toast.makeText(this@SignInActivity, result, Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    override fun onStart() {

        if (viewModel.checkUser()){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        
        super.onStart()
    }

}