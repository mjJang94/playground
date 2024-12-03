package com.mj.local.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthDto(
    val id: Int,
    val token: String,
)
