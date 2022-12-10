package ru.catdimson.bjjmechanics.core.auth

import android.content.Context
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtResponse

interface AuthorizationService {

    fun getAccessTokenFromSharedPref(context: Context): String?

    fun getRefreshTokenFromSharedPref(context: Context): String?

    fun getUserId(context: Context): Int?

    fun saveTokensToSharedPref(token: String, refreshToken: String, context: Context)

    fun saveTokensToSharedPref(jwtResponse: JwtResponse, context: Context)

    fun saveCurrentUserId(userId: Int, context: Context)

    fun clearSharedPreferences(context: Context)

}