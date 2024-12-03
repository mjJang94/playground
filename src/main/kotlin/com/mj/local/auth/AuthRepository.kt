package com.mj.local.auth

import com.mj.local.model.vo.Auth
import com.mj.local.model.vo.User

interface AuthRepository {
    val service: AuthService

    suspend fun addAuth(id: Int, user: User): String
    suspend fun updateAuth(id: Int, user: User): String
    suspend fun removeAuth(id: Int)
}