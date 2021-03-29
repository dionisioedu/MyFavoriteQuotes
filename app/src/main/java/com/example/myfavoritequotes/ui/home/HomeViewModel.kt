package com.example.myfavoritequotes.ui.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myfavoritequotes.data.MyQuoteModel
import com.example.myfavoritequotes.data.MyQuotesDatabase
import com.example.myfavoritequotes.network.MyQuotesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel : ViewModel() {

    private val _quote = MutableLiveData<MyQuoteModel>().apply {
        value = MyQuoteModel(
            "We must return to nature and nature's god.",
            "Luther Burbank",
            "")
    }
    val quote: LiveData<MyQuoteModel> = _quote

    val loadingQuote = MyQuoteModel("Loading...", "Bot", "")

    fun nextQuote() {
        _quote.value = loadingQuote

        viewModelScope.launch {
            try {
                val newQuote = MyQuotesApi.retrofitService.getQuote()
                _quote.value = newQuote[0]
            } catch (e: Exception) {
                _quote.value = MyQuoteModel("Error loading quote: ${e.message}", "Bot", "")
            }
        }
    }

    fun favoriteQuote(context: Context) {
        _quote.value?.let {
            viewModelScope.launch(Dispatchers.IO) {
                MyQuotesDatabase.getInstance(context).myQuotesDao.addFavorite(it)
            }
        }
    }
}