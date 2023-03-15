package com.example.smartwop

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartwop.databinding.ActivitySplash2Binding
import com.example.smartwop.utils.LocaleManager

class SplashActivity2 : AppCompatActivity() {
    lateinit var binding: ActivitySplash2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplash2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.animationView.postDelayed({
            var currentUserID = Firebase().UserId()
            if (currentUserID.isNotEmpty()){
                startActivity(Intent(this, MainActivity::class.java))
            }else{

                startActivity(Intent(this, LoginActivity2::class.java))
            }
            finish()
        },4800)
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleManager.setLocale(newBase))
    }
}