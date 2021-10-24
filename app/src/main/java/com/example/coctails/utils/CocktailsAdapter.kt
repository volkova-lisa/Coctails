package com.example.coctails.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cocktail_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class CocktailsAdapter(onClickListener: ClickListener) : RecyclerView.Adapter<CocktailsAdapter.MainHolder>(){
    private var mListCocktails = mutableListOf<Cocktails>()
    var mOnClickListener : ClickListener? = onClickListener
    val itemClickScope = CoroutineScope(Dispatchers.Main)



    inner class MainHolder(view: View, onClickListener : ClickListener) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val cocktName: TextView = view.cocktail_name
        val cocktImage: ImageView = view.cocktail_pic
        val cocktId : TextView = view.cocktail_id
        val _onClickListener : ClickListener = onClickListener

        init{
            view.setOnClickListener(this)
        }
        override fun onClick(view: View?) {
            itemClickScope.launch {
                delay(200)
                _onClickListener.onItemClick(mListCocktails[adapterPosition].idDrink, view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(com.example.coctails.R.layout.cocktail_item, parent, false)
        return MainHolder(view, mOnClickListener!!)
    }

    override fun getItemCount(): Int = mListCocktails.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.cocktName.text = mListCocktails[position].strDrink
        holder.cocktId.text = mListCocktails[position].idDrink
        Picasso.get().load(mListCocktails[position].strDrinkThumb).into(holder.cocktImage)
    }

    fun setList(list: MutableList<Cocktails>) {
        mListCocktails = list
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onItemClick(drinkId: String?, v: View?)
    }
}