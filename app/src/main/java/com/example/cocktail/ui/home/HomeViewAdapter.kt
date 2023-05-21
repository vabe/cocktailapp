package com.example.cocktail.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktail.R
import com.example.cocktail.databinding.RecyclerViewCocktailsBinding
import com.example.cocktail.model.Cocktail

class HomeViewAdapter(
    private val cocktails: List<Cocktail>,
    private val listener: HomeViewClickListener
    ) : RecyclerView.Adapter<HomeViewAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HomeViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recycler_view_cocktails,
            parent,
            false
        )
    )

    override fun getItemCount(): Int {
        return cocktails.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val cocktail = cocktails[position]
        holder.recyclerViewCocktailsBinding.cocktail = cocktail
        holder.recyclerViewCocktailsBinding.root.setOnClickListener {
            listener.onRecyclerViewItemClick(
                holder.recyclerViewCocktailsBinding.root,
                cocktail
            )
        }
    }

    inner class HomeViewHolder(val recyclerViewCocktailsBinding: RecyclerViewCocktailsBinding)
        : RecyclerView.ViewHolder(recyclerViewCocktailsBinding.root)
}