package com.mj

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.application.hooks.*
import io.ktor.events.EventDefinition
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import kotlinx.serialization.json.Json
import org.slf4j.event.Level

fun Application.configurePlugins() {
    install(ApplicationMonitoringPlugin)
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }
    install(CallLogging) {
        level = Level.INFO
        format { call ->
            val status = call.response.status()
            val httpMethod = call.request.httpMethod.value
            val path = call.request.path()
            val processTime = call.processingTimeMillis()
            "$status $httpMethod - $path response in ${processTime}ms"
        }
    }
}

val ApplicationMonitoringPlugin = createApplicationPlugin(name = "ApplicationMonitoringPlugin") {
    on(MonitoringEvent(ApplicationStarted)) { application ->
        application.log.info("Server is started")
    }
    on(MonitoringEvent(ApplicationStopped)) { application ->
        application.log.info("Server is stopped")
        // Release resources and unsubscribe from events
        application.monitor.unsubscribe(ApplicationStarted) {}
        application.monitor.unsubscribe(ApplicationStopped) {}
    }
    on(ResponseSent) { call ->
        if (call.response.status() == HttpStatusCode.NotFound) {
            this@createApplicationPlugin.application.monitor.raise(NotFoundEvent, call)
        }
    }
}

val NotFoundEvent: EventDefinition<ApplicationCall> = EventDefinition()