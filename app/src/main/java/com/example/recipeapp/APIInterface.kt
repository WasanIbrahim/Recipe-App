package com.example.recipeapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {

    @GET("recipes/")
    fun getRecipe(): Call<ArrayList<RecipesItem>>


    @POST ("recipes/")
    fun postRecipe(@Body testData: RecipesItem): Call<RecipesItem>
}