package com.mj

import com.mj.local.user.UserRepositoryImpl
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val repository = UserRepositoryImpl()
    configureDatabases(repository)
    configurePlugins()

    //서버 실행 여부 테스트용
    configureRouting()
}
