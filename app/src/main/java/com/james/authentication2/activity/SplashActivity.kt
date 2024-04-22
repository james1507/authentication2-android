package com.james.authentication2.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.james.authentication2.databinding.ActivitySplashBinding
import com.james.authentication2.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivitySplashBinding
    private val tokenViewModel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivitySplashBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        tokenViewModel.token.observe(this) { token ->
            if (token != null) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}