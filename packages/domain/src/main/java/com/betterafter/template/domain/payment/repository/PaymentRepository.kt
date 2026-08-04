package com.betterafter.template.domain.payment.repository

import com.betterafter.template.domain.core.DataState
import com.betterafter.template.domain.payment.entity.PaymentEntity

interface PaymentRepository {
    suspend fun getPayments(): DataState<List<PaymentEntity>>
}
