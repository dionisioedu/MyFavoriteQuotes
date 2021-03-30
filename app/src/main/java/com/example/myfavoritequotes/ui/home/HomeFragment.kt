package com.example.myfavoritequotes.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.myfavoritequotes.R
import com.example.myfavoritequotes.data.MyQuotesDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val buttonNewQuote = root.findViewById<ImageButton>(R.id.button_new_quote)
        buttonNewQuote?.let {
            it.setOnClickListener {
                homeViewModel.nextQuote()
            }
        }

        val buttonFavorite = root.findViewById<ImageButton>(R.id.button_favorite)
        buttonFavorite?.let {
            it.setOnClickListener {
                homeViewModel.favoriteQuote(requireContext().applicationContext)

                Toast.makeText(context, "New Favorite Added!", Toast.LENGTH_SHORT).show()
            }
        }

        val textViewQuote = root.findViewById<TextView>(R.id.textView_quote)
        homeViewModel.quote.observe(viewLifecycleOwner, Observer {
            it?.let {
                textViewQuote.text = it.text
            }
        })

        return root
    }
}