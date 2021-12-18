package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var myRV: RecyclerView
    val APIList = ArrayList<RecipesItem>()
    val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myRV = findViewById(R.id.myRV)
        myRV.layoutManager = LinearLayoutManager(this)

        getAPI()

        binding.Addbutton.setOnClickListener {
            val intent = Intent(this, addRecipeActivity::class.java)
            startActivity(intent)

        }
    }

    private fun getAPI() {

        val dataReceived = apiInterface?.getRecipe()

        dataReceived?.enqueue(object : Callback<ArrayList<RecipesItem>> {
            override fun onResponse(
                call: Call<ArrayList<RecipesItem>>,
                response: Response<ArrayList<RecipesItem>>
            ) {
                val myResponse = response.body()
                if (myResponse != null) {
                    for (i in myResponse) {
                        APIList.add(i)
                        myRV.adapter = RecyclerViewAdapter(APIList)
                        myRV.adapter?.notifyDataSetChanged()
                        println("here is the APIList $APIList ")
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<RecipesItem>>, t: Throwable) {
                Log.d("response", "failed to get data , ${t.message}")

            }
        })
    }
}