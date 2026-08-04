package com.betterafter.template.data.payment.datasource

import com.betterafter.template.data.payment.local.PaymentDao
import com.betterafter.template.data.payment.local.PaymentEntityRoom
import javax.inject.Inject

class PaymentLocalDataSource @Inject constructor(
    private val dao: PaymentDao,
) {
    suspend fun getPayments(): List<PaymentEntityRoom> = dao.getAll()

    suspend fun replaceAll(items: List<PaymentEntityRoom>) {
        dao.clear()
        dao.insertAll(items)
    }
}
