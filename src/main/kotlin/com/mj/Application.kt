package com.mj

import com.mj.local.auth.AuthRepositoryImpl
import com.mj.local.user.UserRepositoryImpl
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configurePlugins()
    configureDatabases(userRepo = UserRepositoryImpl(), authRepo = AuthRepositoryImpl())
    configureAuth(userRepo = UserRepositoryImpl(), authRepo = AuthRepositoryImpl())
    //서버 실행 여부 테스트용
    configureRouting()
}
