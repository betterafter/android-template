package com.betterafter.template.domain.payment.entity

data class PaymentEntity(
    val id: String,
    val amount: Int,
    val status: String,
)
