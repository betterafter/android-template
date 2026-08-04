package com.betterafter.template.domain.payment.usecase

import com.betterafter.template.domain.core.DataState
import com.betterafter.template.domain.payment.entity.PaymentEntity
import com.betterafter.template.domain.payment.repository.PaymentRepository
import javax.inject.Inject

class PaymentUseCase @Inject constructor(
    private val paymentRepository: PaymentRepository,
) {
    suspend operator fun invoke(): DataState<List<PaymentEntity>> {
        return paymentRepository.getPayments()
    }
}
