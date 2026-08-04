package com.betterafter.template.data.payment.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PaymentDao {
    @Query("SELECT * FROM payments")
    suspend fun getAll(): List<PaymentEntityRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<PaymentEntityRoom>)

    @Query("DELETE FROM payments")
    suspend fun clear()
}
