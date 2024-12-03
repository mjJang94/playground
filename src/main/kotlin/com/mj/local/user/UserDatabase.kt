package com.mj.local.user

import org.jetbrains.exposed.sql.Database

internal val userDatabase = Database.connect(
    url = "jdbc:h2:~/playground_user",
    user = "user",
    driver = "org.h2.Driver",
    password = "dnsthrh1",
)
