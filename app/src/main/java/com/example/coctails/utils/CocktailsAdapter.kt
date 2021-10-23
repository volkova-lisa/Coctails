package com.example.coctails.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coctails.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cocktail_item.view.*
import kotlinx.android.synthetic.main.drink_item.view.*

class CocktailsAdapter : RecyclerView.Adapter<CocktailsAdapter.MainHolder>(){
    private var mListCocktails = mutableListOf<Cocktails>()


    class MainHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cocktName: TextView = view.cocktail_name
        val cocktImage: ImageView = view.cocktail_pic
        val cocktId : TextView = view.cocktail_id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cocktail_item, parent, false)
        return MainHolder(view)
    }

    override fun getItemCount(): Int = mListCocktails.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.cocktName.text = mListCocktails[position].strDrink
        holder.cocktId.text = mListCocktails[position].idDrink
        Picasso.get().load(mListCocktails[position].strDrinkThumb).into(holder.cocktImage)
    }

//                val imageUri = "https://i.imgur.com/tGbaZCY.jpg"
//                val ivBasicImage: ImageView = findViewById(R.id.ivBasicImage) as ImageView
//                Picasso.with(context).load(imageUri).into(ivBasicImage)

    fun setList(list: MutableList<Cocktails>) {
        mListCocktails = list
        notifyDataSetChanged()
    }
}