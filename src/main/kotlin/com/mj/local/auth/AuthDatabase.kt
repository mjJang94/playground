package com.mj.local.auth

import org.jetbrains.exposed.sql.Database

internal val authDatabase = Database.connect(
    url = "jdbc:h2:~/playground_auth",
    user = "auth",
    driver = "org.h2.Driver",
    password = "dnsthrh1",
)