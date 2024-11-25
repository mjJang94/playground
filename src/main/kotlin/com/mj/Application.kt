package com.mj

import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureHTTP()
    configureDatabases()
    configureRouting()
    callLoggingConfiguration()
    configureContentNegotiation()
}