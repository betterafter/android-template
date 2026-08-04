package com.betterafter.template.data.payment.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "payments")
data class PaymentEntityRoom(
    @PrimaryKey val id: String,
    val amount: Int,
    val status: String,
)
