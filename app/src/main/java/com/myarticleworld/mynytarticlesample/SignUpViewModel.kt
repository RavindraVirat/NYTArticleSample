package com.myarticleworld.mynytarticlesample

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {

    var userName = ""
    var email = ""
    var password = ""

    private val _isSignUpDataValid = MutableLiveData<String>()
    var isSignUpDataValid: LiveData<String> = _isSignUpDataValid

    fun validateDetails() {
        if (userName.isEmpty()) _isSignUpDataValid.value = "Please enter user name."
        else if (userName.length < 3) _isSignUpDataValid.value =
            "User name should be min. 3 characters long."
        else if (!isValidEmail(email)) _isSignUpDataValid.value = "Please enter valid email."
        else if (!isValidPassword(password)) _isSignUpDataValid.value =
            "Password must contain at least 8 characters with at least 1 number, 1 uppercase & 1 special character."
        else _isSignUpDataValid.value = "valid"
    }

    private fun isValidEmail(email: String): Boolean {
        return  Patterns.EMAIL_ADDRESS.matcher(email).matches()
//        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\.+[a-z]+"
//        return email.matches(emailPattern.toRegex())
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordREGEX = Pattern.compile(
            "^" + "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 8 characters
                    "$"
        );
        return passwordREGEX.matcher(password).matches()
    }
}