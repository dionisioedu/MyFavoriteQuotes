package com.example.myfavoritequotes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(MyQuoteModel::class), version = 1)
abstract class MyQuotesDatabase : RoomDatabase() {

    abstract val myQuotesDao : MyQuotesDao

    companion object {

        @Volatile
        private var INSTANCE: MyQuotesDatabase? = null

        fun getInstance(context: Context): MyQuotesDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                MyQuotesDatabase::class.java, "my_quotes.db")
                .build()
    }

}