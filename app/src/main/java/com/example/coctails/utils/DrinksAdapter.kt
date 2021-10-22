package com.example.coctails.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coctails.R
import kotlinx.android.synthetic.main.drink_item.view.*

class DrinksAdapter : RecyclerView.Adapter<DrinksAdapter.MainHolder>() {

    private var mListDrinks = emptyList<Drinks>()


    class MainHolder(view: View) : RecyclerView.ViewHolder(view) {
        val drinkName: TextView = view.drink_name
        //val drinkImage: ImageView = view.drink_description
        val drinkId : TextView = view.drink_id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.drink_item, parent, false)
        return MainHolder(view)
    }

    override fun getItemCount(): Int = mListDrinks.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.drinkName.text = mListDrinks[position].strDrink
        holder.drinkId.text = mListDrinks[position].idDrink
    }

    fun setList(list: List<Drinks>) {
        mListDrinks = list
        notifyDataSetChanged()
    }

}