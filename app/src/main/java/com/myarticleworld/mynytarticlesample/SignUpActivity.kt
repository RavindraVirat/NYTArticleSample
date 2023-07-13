package com.myarticleworld.mynytarticlesample

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.myarticleworld.mynytarticlesample.databinding.ActivitySignupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        binding.viewModel = viewModel

        viewModel.isSignUpDataValid.observe(this) {
            if (it == "valid") goToDashboardScreen()
            else Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        binding.btnSubmit.setOnClickListener {
            viewModel.validateDetails()
        }
    }

    private fun goToDashboardScreen() {
        val intent = Intent(this, Dashboard::class.java)
        startActivity(intent)
        finish()
    }
}