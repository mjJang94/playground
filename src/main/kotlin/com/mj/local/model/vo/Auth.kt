package com.mj.local.model.vo

import kotlinx.serialization.Serializable

@Serializable
data class Auth(
    val id: Int,
    val token: String,
)