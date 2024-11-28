package com.mj.local.user

import com.mj.local.model.User

class UserRepositoryImpl : UserRepository {
    override val service: UserService
        get() = UserService(userDatabase)

    override suspend fun addUser(user: User): Int =
        service.create(user)


    override suspend fun getUser(id: Int): User? =
        service.read(id)


    override suspend fun updateUser(id: Int, user: User) =
        service.update(id, user)


    override suspend fun removeUser(id: Int) =
        service.delete(id)
}