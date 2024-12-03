package com.mj.local.model.vo

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String,
    val age: Int,
)
