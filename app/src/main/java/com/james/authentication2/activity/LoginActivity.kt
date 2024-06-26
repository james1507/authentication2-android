package com.james.authentication2.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.james.authentication2.databinding.ActivityLoginBinding
import com.james.authentication2.helpers.ApiResponse
import com.james.authentication2.model.LoginBody
import com.james.authentication2.viewmodel.AuthViewModel
import com.james.authentication2.viewmodel.CoroutinesErrorHandler
import com.james.authentication2.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private val authViewModel: AuthViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityLoginBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        authViewModel.token.observe(this) { token ->
            if (token != null) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }

        val status = mBinding.status

        viewModel.loginResponse.observe(this) {
            when (it) {
                is ApiResponse.Failure -> status.text = it.msg
                ApiResponse.Loading -> status.text = "Loading"
                is ApiResponse.Success -> {
                    authViewModel.saveAuth(it.data.results.token, it.data.results.id)
                }
            }
        }

        mBinding.loginButton.setOnClickListener {
            viewModel.login(
                LoginBody(
                    mBinding.email.text.toString(),
                    mBinding.password.text.toString()
                ), object :
                    CoroutinesErrorHandler {
                    override fun onError(message: String) {
                        status.text = "Error! $message"
                    }
                })
        }
    }
}