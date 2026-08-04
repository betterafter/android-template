package com.betterafter.template.data.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.betterafter.template.data.payment.local.PaymentDao
import com.betterafter.template.data.payment.local.PaymentEntityRoom

@Database(
    entities = [PaymentEntityRoom::class],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun paymentDao(): PaymentDao
}
