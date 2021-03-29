package com.example.myfavoritequotes.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.myfavoritequotes.R
import com.example.myfavoritequotes.data.MyQuotesDatabase

class FavoritesFragment : Fragment() {

    private lateinit var favoritesViewModel: FavoritesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(this.activity).application
        val dataSource = MyQuotesDatabase.getInstance(requireContext().applicationContext).myQuotesDao
        val viewModelFactory = FavoritesViewModelFactory(dataSource, application)

        favoritesViewModel = ViewModelProvider(this, viewModelFactory).get(FavoritesViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_favorites, container, false)

        val adapter = FavoritesAdapter()
        val recyclerView = root.findViewById<RecyclerView>(R.id.listView_favorites)
        recyclerView?.let {
            it.adapter = adapter
        }

        favoritesViewModel.favorites.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return root
    }
}