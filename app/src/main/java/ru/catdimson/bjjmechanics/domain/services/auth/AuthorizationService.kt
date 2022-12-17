package ru.catdimson.bjjmechanics.domain.services.auth

import android.content.Context
import ru.catdimson.bjjmechanics.domain.entities.system.ShortUserInfo
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtResponse

interface AuthorizationService {

    fun getAccessTokenFromSharedPref(context: Context): String?

    fun getRefreshTokenFromSharedPref(context: Context): String?

    fun getUserId(context: Context): Int?

    fun getUserLogin(context: Context): String?

    fun getUserInfo(context: Context): ShortUserInfo

    fun saveTokensToSharedPref(token: String, refreshToken: String, context: Context)

    fun saveTokensToSharedPref(jwtResponse: JwtResponse, context: Context)

    fun saveCurrentUserInfo(userInfo: ShortUserInfo?, context: Context)

    fun clearSharedPreferences(context: Context)

}