package com.james.authentication2.activity

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.james.authentication2.databinding.ActivityHomeBinding
import com.james.authentication2.helpers.ApiResponse
import com.james.authentication2.viewmodel.AuthViewModel
import com.james.authentication2.viewmodel.CoroutinesErrorHandler
import com.james.authentication2.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityHomeBinding
    private val authViewModel: AuthViewModel by viewModels()
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityHomeBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)


        getUserInfo()
        logout()
    }

    private fun getUserInfo() {
        val status = mBinding.status

        authViewModel.userId.observe(this) { userId ->
            if (userId != null) {
                viewModel.getProfile(
                    userId, object :
                        CoroutinesErrorHandler {
                        override fun onError(message: String) {
                            status.text = "Error! $message"
                        }
                    })
            }
        }

        viewModel.profileResponse.observe(this) {
            when (it) {
                is ApiResponse.Failure -> status.text = it.msg
                ApiResponse.Loading -> status.text = "Loading"
                is ApiResponse.Success -> {
                    status.visibility = View.GONE
                    mBinding.userInfo.visibility = View.VISIBLE
                    mBinding.userId.text = it.data.results.id
                    mBinding.name.text = it.data.results.userName
                    mBinding.emailAccount.text = it.data.results.email
                }
            }
        }
    }

    private fun logout() {
        mBinding.btnLogout.setOnClickListener {
            try {
                authViewModel.deleteAuth()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } catch (ex : ActivityNotFoundException) {
                Log.e(HomeActivity::class.java.name, "Error: ${ex.message}")
            }

        }
    }
}