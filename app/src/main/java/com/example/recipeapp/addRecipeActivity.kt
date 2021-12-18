package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.recipeapp.databinding.ActivityAddRecipeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class addRecipeActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddRecipeBinding

    val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitButton.setOnClickListener {
            postAPI()
            binding.authorText2.text = null
            binding.ingredientsText2.text = null
            binding.titleText2.text = null
            binding.instructionsText2.text = null


        }
        binding.cancelButton.setOnClickListener {

            binding.titleText2.text = null
            binding.authorText2.text = null
            binding.ingredientsText2.text = null
            binding.instructionsText2.text = null

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun postAPI() {
        apiInterface?.postRecipe(
            RecipesItem(
                binding.titleText2.text.toString(),
                binding.authorText2.text.toString(),
                binding.ingredientsText2.text.toString(),
                binding.instructionsText2.text.toString()
            )
        )?.enqueue(object : Callback<RecipesItem> {
            override fun onResponse(call: Call<RecipesItem>, response: Response<RecipesItem>) {
                Log.d("MAIN", "Successfully posted")
                println("here is the data entered ${binding.titleText2.text} and ${binding.authorText2.text} and ${binding.ingredientsText2.text} and ${binding.instructionsText2.text} ")


            }

            override fun onFailure(call: Call<RecipesItem>, t: Throwable) {
                Log.d("MAIN", "Something went wrong!")
            }

        })
    }
}