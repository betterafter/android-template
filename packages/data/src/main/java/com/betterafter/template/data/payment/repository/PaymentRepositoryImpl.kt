package com.betterafter.template.data.payment.repository

import com.betterafter.template.data.payment.datasource.PaymentLocalDataSource
import com.betterafter.template.data.payment.datasource.PaymentRemoteDataSource
import com.betterafter.template.data.payment.mapper.PaymentMapper
import com.betterafter.template.domain.core.DataState
import com.betterafter.template.domain.payment.entity.PaymentEntity
import com.betterafter.template.domain.payment.repository.PaymentRepository
import javax.inject.Inject

/**
 * Remote → 캐시(Room) → Domain Entity 흐름.
 * 네트워크 실패 시 로컬 캐시로 폴백한다.
 */
class PaymentRepositoryImpl @Inject constructor(
    private val remoteDataSource: PaymentRemoteDataSource,
    private val localDataSource: PaymentLocalDataSource,
    private val mapper: PaymentMapper,
) : PaymentRepository {

    override suspend fun getPayments(): DataState<List<PaymentEntity>> {
        return DataState.guard {
            try {
                val dtos = remoteDataSource.getPayments()
                val entities = mapper.toEntityList(dtos)
                localDataSource.replaceAll(mapper.toRoomList(entities))
                entities
            } catch (remoteError: Throwable) {
                val cached = mapper.fromRoomList(localDataSource.getPayments())
                if (cached.isNotEmpty()) {
                    cached
                } else {
                    throw remoteError
                }
            }
        }
    }
}
