package com.betterafter.template.data.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.betterafter.template.data.user.local.UserDao
import com.betterafter.template.data.user.local.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
