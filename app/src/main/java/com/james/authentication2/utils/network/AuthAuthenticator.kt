package com.james.authentication2.utils.network

import com.james.authentication2.model.LoginResponse
import com.james.authentication2.model.RefreshTokenBody
import com.james.authentication2.model.RefreshTokenResponse
import com.james.authentication2.utils.constant.NetworkConstant.BASE_URL
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthAuthenticator @Inject constructor(private val tokenManager: TokenManager) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val token = runBlocking {
            tokenManager.getToken().first()
        }
        val refreshToken = RefreshTokenBody(token ?: "")
        return runBlocking {
            val newToken = getNewToken(refreshToken)

            if (!newToken.isSuccessful || newToken.body() == null) { //Couldn't refresh the token, so restart the login process
                tokenManager.deleteToken()
            }

            newToken.body()?.let {
                tokenManager.saveToken(it.results.token)
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${it.results.token}")
                    .build()
            }
        }
    }

    private suspend fun getNewToken(refreshToken: RefreshTokenBody): retrofit2.Response<RefreshTokenResponse> {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        val service = retrofit.create(APIConsumer::class.java)
        return service.refreshToken(refreshToken)
    }

}