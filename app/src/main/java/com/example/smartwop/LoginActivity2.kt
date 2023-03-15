package com.example.smartwop

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Toast
import com.example.smartwop.databinding.ActivityLogin2Binding
import com.example.smartwop.model.UserModel
import com.example.smartwop.utils.LocaleManager
import com.google.firebase.auth.FirebaseAuth

class LoginActivity2 : BaseActivity2() {
        lateinit var binding: ActivityLogin2Binding
        lateinit var auth: FirebaseAuth
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityLogin2Binding.inflate(layoutInflater)
            setContentView(binding.root)
            auth = FirebaseAuth.getInstance()
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
            binding.btnLogIn.setOnClickListener {
                logInRegisteredUser()
            }

            binding.tvSign.setOnClickListener {
                startActivity(Intent(this,SignActivity2::class.java))
            }
            binding.tvForgotPassword.setOnClickListener {
                startActivity(Intent(this,SignActivity2::class.java))
                finish()
            }
        }

        fun logInSuccess(user: UserModel){
            hideProgressDialog()
            startActivity(Intent(this,MainActivity::class.java))
        }
        private fun logInRegisteredUser() {
            // Here we get the text from editText and trim the space
            val email: String = binding.etEmail.text.toString().trim { it <= ' ' }
            val password: String = binding.etPassword.text.toString().trim { it <= ' ' }

            if (validateForm(email, password)) {
                // Show the progress dialog.
                showProgressDialog()

                // Sign-In using FirebaseAuth
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Firebase().logIn(this@LoginActivity2)
                        } else {
                            Toast.makeText(
                                this@LoginActivity2,
                                task.exception!!.message,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
        }
        private fun validateForm(email: String, password: String): Boolean {
            return when {
                TextUtils.isEmpty(email) -> {
                    showErrorSnackBar("Please enter email.")
                    false
                }
                TextUtils.isEmpty(password) -> {
                    showErrorSnackBar("Please enter password.")
                    false
                }
                else -> {
                    true
                }
            }
        }

        override fun attachBaseContext(newBase: Context?) {
            super.attachBaseContext(LocaleManager.setLocale(newBase))
        }
    }
