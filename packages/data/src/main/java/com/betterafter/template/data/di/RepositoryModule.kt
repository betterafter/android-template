package com.betterafter.template.data.di

import com.betterafter.template.data.payment.repository.PaymentRepositoryImpl
import com.betterafter.template.domain.payment.repository.PaymentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPaymentRepository(
        impl: PaymentRepositoryImpl,
    ): PaymentRepository
}
