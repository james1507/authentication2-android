package com.james.authentication2.utils.network

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.james.authentication2.utils.constant.LocalStorageConstant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthManager(private val context: Context) {
    companion object {
        private val Context.dataStore by preferencesDataStore("jwt_token")
        private val TOKEN_KEY = stringPreferencesKey(LocalStorageConstant.TOKEN)
        private val USER_ID = stringPreferencesKey(LocalStorageConstant.USER_ID)
    }

    fun getToken(): Flow<String?> {

        return context.dataStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    suspend fun deleteToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }

    fun getUserId(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[USER_ID]
        }
    }

    suspend fun saveUserId(userId: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID] = userId
        }
    }

    suspend fun deleteUserId() {
        context.dataStore.edit { preferences ->
            preferences.remove(USER_ID)
        }
    }
}