package com.mj

import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureDatabases()
    configurePlugins()

    //서버 실행 여부 테스트용
    configureRouting()
}
