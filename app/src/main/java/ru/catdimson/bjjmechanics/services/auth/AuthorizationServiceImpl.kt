package ru.catdimson.bjjmechanics.services.auth

import android.content.Context
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtResponse

class AuthorizationServiceImpl : AuthorizationService {

    companion object {
        private const val AUTH_DATAS = "AUTH_DATAS"
        private const val ACCESS_TOKEN = "TOKEN"
        private const val REFRESH_TOKEN = "REFRESH_TOKEN"
        private const val USER_ID = "USER_ID"
    }

    override fun getAccessTokenFromSharedPref(context: Context): String? {
        return getValueString(ACCESS_TOKEN, context)
    }

    override fun getRefreshTokenFromSharedPref(context: Context): String? {
        return getValueString(REFRESH_TOKEN, context)
    }

    override fun saveTokensToSharedPref(token: String, refreshToken: String, context: Context) {
        saveValueString(ACCESS_TOKEN, token, context)
        saveValueString(REFRESH_TOKEN, refreshToken, context)
    }

    override fun saveTokensToSharedPref(jwtResponse: JwtResponse, context: Context) {
        saveValueString(ACCESS_TOKEN, jwtResponse.accessToken, context)
        saveValueString(REFRESH_TOKEN, jwtResponse.refreshToken, context)
    }

    override fun saveCurrentUserId(userId: Int, context: Context) {
        saveValueInt(USER_ID, userId, context)
    }

    override fun clearSharedPreferences(context: Context) {
        val sp = context.getSharedPreferences(AUTH_DATAS, Context.MODE_PRIVATE)
        sp.edit().clear().apply()
    }

    override fun getUserId(context: Context): Int? {
        return getValueInt(USER_ID, context)
    }

    private fun getValueString(KEY: String, context: Context): String? {
        return context.getSharedPreferences(AUTH_DATAS, Context.MODE_PRIVATE).getString(KEY, null)
    }

    private fun getValueInt(KEY: String, context: Context): Int? {
        return context.getSharedPreferences(AUTH_DATAS, Context.MODE_PRIVATE).getInt(KEY, 0)
    }

    private fun saveValueString(KEY: String, VALUE: String, context: Context) {
        val editor = context.getSharedPreferences(AUTH_DATAS, Context.MODE_PRIVATE).edit()
        editor.putString(KEY, VALUE)
        editor.apply()
    }

    private fun saveValueInt(KEY: String, VALUE: Int, context: Context) {
        val editor = context.getSharedPreferences(AUTH_DATAS, Context.MODE_PRIVATE).edit()
        editor.putInt(KEY, VALUE)
        editor.apply()
    }
}