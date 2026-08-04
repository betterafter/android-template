package com.betterafter.template.data.payment.api

import com.betterafter.template.data.payment.dto.PaymentDto
import retrofit2.http.GET

interface PaymentApi {
    @GET("payments")
    suspend fun getPayments(): List<PaymentDto>
}
