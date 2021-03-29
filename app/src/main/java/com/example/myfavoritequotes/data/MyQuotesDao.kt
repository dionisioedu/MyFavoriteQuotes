package com.example.myfavoritequotes.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MyQuotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFavorite(quote: MyQuoteModel)

    @Delete
    fun delFavorite(quote: MyQuoteModel)

    @Query("SELECT * FROM my_quotes")
    fun getQuotes() : LiveData<List<MyQuoteModel>>

}