package com.mj.local.auth

import com.mj.TokenManager
import com.mj.local.model.vo.User
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

class AuthService(database: Database) {

    object Auth : Table() {
        val id = integer("id")
        val token = varchar("name", length = 1000)

        override val primaryKey = PrimaryKey(id)
    }

    init {
        transaction(database) {
            SchemaUtils.create(Auth)
        }
    }

    suspend fun create(userId: Int, user: User): String = dbQuery {
        Auth.insert {
            it[id] = userId
            it[token] = TokenManager.generateToken(user)
        }[Auth.token]
    }

    suspend fun update(userId: Int, user: User): String {
        val newToken = TokenManager.generateToken(user)
        dbQuery {
            Auth.update({ Auth.id eq userId}) {
                it[id] = userId
                it[token] = newToken
            }
        }
        return newToken
    }

    suspend fun remove(id: Int) {
        dbQuery {
            Auth.deleteWhere { Auth.id.eq(id) }
        }
    }


    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}