package ru.catdimson.bjjmechanics.core.auth

import android.content.Context
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtResponse

class AuthorizationServiceImpl : AuthorizationService {

    companion object {
        private const val AUTH_DATAS = "AUTH_DATAS"
        private const val ACCESS_TOKEN = "TOKEN"
        private const val REFRESH_TOKEN = "REFRESH_TOKEN"
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

    private fun getValueString(KEY: String, context: Context): String? {
        return context.getSharedPreferences(AUTH_DATAS, Context.MODE_PRIVATE).getString(KEY, null)
    }

    private fun saveValueString(KEY: String, VALUE: String, context: Context) {
        val editor = context.getSharedPreferences(AUTH_DATAS, Context.MODE_PRIVATE).edit()
        editor.putString(KEY, VALUE)
        editor.apply()
    }
}