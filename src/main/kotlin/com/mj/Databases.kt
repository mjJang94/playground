package com.mj

import com.mj.local.auth.AuthRepository
import com.mj.local.model.dto.AuthDto
import com.mj.local.model.vo.User
import com.mj.local.user.UserRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureDatabases(
    userRepo: UserRepository,
    authRepo: AuthRepository,
) {
    routing {
        // Create user
        post("/users/sign-in") {
            val user = call.receive<User>()
            val response = runCatching {
                val id = userRepo.addUser(user)
                authRepo.addAuth(id, user)
            }.getOrNull()

            if (response != null) {
                call.respond(HttpStatusCode.Created, response)
            } else {
                call.respond(HttpStatusCode.ExpectationFailed)
            }
        }

        // Read user
        get("/users/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            val user = userRepo.getUser(id)
            if (user != null) {
                call.respond(HttpStatusCode.OK, user)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        // Update user
        put("/users/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            val user = call.receive<User>()
            userRepo.updateUser(id, user)
            call.respond(HttpStatusCode.OK)
        }

        // Delete user
        delete("/users/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            userRepo.removeUser(id)
            authRepo.removeAuth(id)
            call.respond(HttpStatusCode.OK)
        }
    }
}


fun Application.configureAuth(
    userRepo: UserRepository,
    authRepo: AuthRepository,
) {
    routing {
        // Update auth
        put("/auth/{id}"){
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            val user = userRepo.getUser(id)
            if (user != null){
                val auth = authRepo.updateAuth(id, user)
                call.respond(HttpStatusCode.OK, auth)
            }else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}
