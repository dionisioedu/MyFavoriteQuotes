package com.example.myfavoritequotes.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfavoritequotes.data.MyQuoteModel
import com.example.myfavoritequotes.network.MyQuotesApi
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel : ViewModel() {

    private val _quote = MutableLiveData<MyQuoteModel>().apply {
        value = MyQuoteModel(
            "0EfNwqiOsZ",
            "We must return to nature and nature's god.",
            "Luther Burbank")
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

    fun favoriteQuote() {
        Log.d("Teste", "favoriteQuote()")
    }
}