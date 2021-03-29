package com.example.myfavoritequotes.ui.favorites

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myfavoritequotes.data.MyQuoteModel
import com.example.myfavoritequotes.data.MyQuotesDao
import com.example.myfavoritequotes.databinding.FavoriteItemBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoritesAdapter(
    val myQuotesDao: MyQuotesDao
) : ListAdapter<MyQuoteModel, FavoritesAdapter.ViewHolder>(QuotesComparator()) {

    class ViewHolder private constructor(val binding: FavoriteItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(quote: MyQuoteModel, myQuotesDao: MyQuotesDao) {
            binding.textViewItemQuote.text = quote.text
            binding.textViewItemAuthor.text = quote.author
            binding.buttonRemoveItem.setOnClickListener {
                Log.d("removeItem", "$quote")
                GlobalScope.launch {
                    myQuotesDao.delFavorite(quote)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FavoriteItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myQuote = getItem(position)
        myQuote?.let {
            holder.bind(it, myQuotesDao)
        }
    }

    class QuotesComparator : DiffUtil.ItemCallback<MyQuoteModel>() {
        override fun areItemsTheSame(oldItem: MyQuoteModel, newItem: MyQuoteModel): Boolean {
            return oldItem.text == newItem.text && oldItem.author == newItem.author
        }

        override fun areContentsTheSame(oldItem: MyQuoteModel, newItem: MyQuoteModel): Boolean {
            return oldItem.text == newItem.text && oldItem.author == newItem.author
        }
    }
}