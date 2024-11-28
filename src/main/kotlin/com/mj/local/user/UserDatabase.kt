package com.mj.local.user

import org.jetbrains.exposed.sql.Database

internal val userDatabase = Database.connect(
    url = "jdbc:h2:~/playground",
    user = "sa",
    driver = "org.h2.Driver",
    password = "",
)
