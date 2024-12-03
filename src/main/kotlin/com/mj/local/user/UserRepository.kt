package com.mj.local.user

import com.mj.local.model.vo.User

interface UserRepository {
    val service: UserService

    suspend fun addUser(user: User): Int
    suspend fun getUser(id: Int): User?
    suspend fun updateUser(id: Int, user: User)
    suspend fun removeUser(id: Int)
}