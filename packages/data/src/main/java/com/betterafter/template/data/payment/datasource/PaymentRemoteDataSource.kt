package com.betterafter.template.data.payment.datasource

import com.betterafter.template.data.payment.api.PaymentApi
import com.betterafter.template.data.payment.dto.PaymentDto
import javax.inject.Inject

class PaymentRemoteDataSource @Inject constructor(
    private val api: PaymentApi,
) {
    suspend fun getPayments(): List<PaymentDto> = api.getPayments()
}
