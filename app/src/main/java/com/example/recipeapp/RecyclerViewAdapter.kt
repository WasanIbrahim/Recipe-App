package com.example.recipeapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.databinding.ItemRowBinding

class RecyclerViewAdapter (var APIList: ArrayList<RecipesItem>): RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewAdapterView>(){
    class RecyclerViewAdapterView (val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapterView {
        return RecyclerViewAdapterView(ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapterView, position: Int) {



        var title = APIList[position]
        var author = APIList[position]
        var ingredients = APIList[position]
        var instructions = APIList[position]


        holder.binding.apply {
            titleText.text = title.title
            authorText.text = author.author
            ingredientsText.text = ingredients.ingredients
            instructionsText.text = instructions.instructions

        }

    }

    override fun getItemCount(): Int {
        return APIList.size
    }













}












