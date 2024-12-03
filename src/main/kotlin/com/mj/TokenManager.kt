package com.mj

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.mj.local.model.vo.User
import java.util.*
import kotlin.time.Duration.Companion.days

object TokenManager {
    fun generateToken(user: User): String {
        return JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("name", user.name)
            .withExpiresAt(getExpirationTime())
            .sign(Algorithm.HMAC256(secret))
    }

    const val issuer = "issuer"
    const val audience = "audience"
    const val secret = "secret"
    const val realm = "realm"

    private fun getExpirationTime() = Date(System.currentTimeMillis() + 1.days.inWholeMilliseconds)
}