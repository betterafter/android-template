package com.betterafter.template.data.payment.dto

import com.google.gson.annotations.SerializedName

data class PaymentDto(
    @SerializedName("id") val id: String,
    @SerializedName("amount") val amount: Int,
    @SerializedName("status") val status: String,
)
