package com.diegohenrick.vespanews.feature.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.diegohenrick.vespanews.feature.data.local.dao.UserDao
import com.diegohenrick.vespanews.feature.data.local.entity.User

@Database(entities = [User::class], version = 1)
abstract class UserRoomDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserRoomDatabase? = null

        fun getDatabase(context: Context): UserRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserRoomDatabase::class.java,
                    "user_Database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}