package com.ne.samplenewars

import com.ne.samplenewars.SuffName.FavName
import com.ne.samplenewars.SuffName.SuffName
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SuffName::class, FavName::class], version = 1)
abstract class SuffDatabase : RoomDatabase() {

    abstract fun suffDao(): SuffDao
    abstract fun favDao(): FavDao

    companion object {
        @Volatile
        private var INSTANCE: SuffDatabase? = null

        fun getDatabase(context: Context): SuffDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SuffDatabase::class.java,
                    "suff_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
