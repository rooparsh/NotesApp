package com.darklabs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommonResponse<T>(
    @SerialName("data") val data: T,
    @SerialName("message") val message: String
)
