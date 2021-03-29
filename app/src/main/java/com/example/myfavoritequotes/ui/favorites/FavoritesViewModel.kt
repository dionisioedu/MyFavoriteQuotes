package com.example.myfavoritequotes.ui.favorites

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfavoritequotes.data.MyQuoteModel
import com.example.myfavoritequotes.data.MyQuotesDao
import com.example.myfavoritequotes.data.MyQuotesDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel(
    val database: MyQuotesDao,
    val application: Application
) : ViewModel() {

    val favorites = database.getQuotes()

    fun delFavorite(newQuote: MyQuoteModel) {
        viewModelScope.launch(Dispatchers.IO) {
            database.delFavorite(newQuote)
        }
    }

}