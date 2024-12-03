package com.mj.local.auth

import com.mj.local.model.vo.User

class AuthRepositoryImpl: AuthRepository {
    override val service: AuthService
        get() = AuthService(authDatabase)

    override suspend fun addAuth(id: Int, user: User): String =
        service.create(id, user)

    override suspend fun updateAuth(id: Int, user: User): String =
        service.update(id, user)

    override suspend fun removeAuth(id: Int) {
        service.remove(id)
    }
}