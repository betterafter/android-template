package com.betterafter.template.data.payment.api

import com.betterafter.template.data.payment.dto.PaymentDto
import kotlinx.coroutines.delay

/**
 * 템플릿 데모용 Fake API.
 * 실제 서버 연동 시 [PaymentApi]를 Retrofit으로 교체하세요. (NetworkModule 참고)
 */
class FakePaymentApi : PaymentApi {
    override suspend fun getPayments(): List<PaymentDto> {
        delay(500)
        return listOf(
            PaymentDto(id = "pay_001", amount = 12_000, status = "COMPLETED"),
            PaymentDto(id = "pay_002", amount = 45_500, status = "PENDING"),
            PaymentDto(id = "pay_003", amount = 9_900, status = "FAILED"),
        )
    }
}
