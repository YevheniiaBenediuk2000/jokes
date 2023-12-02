package com.example.jokes

import android.content.Context
import android.provider.ContactsContract.Data
import android.widget.Toast
import retrofit.*

class APiCall {
    fun getjokes(context: Context, category: String? = null, callback: (DataModel) -> Unit) {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.chucknorris.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: ApiService = retrofit.create<ApiService>(ApiService::class.java)

        val call: Call<DataModel> = service.getjokes(category)

        call.enqueue(object : Callback<DataModel> {
            override fun onResponse(response: Response<DataModel>?, retrofit: Retrofit?) {
                if(response!!.isSuccess){
                    val jokes: DataModel = response.body() as DataModel
                    callback(jokes)
                }
            }

            override fun onFailure(t: Throwable?) {
                Toast.makeText(context, "Request Fail", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getCategories(context: Context, callback: (List<String>) -> Unit) {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.chucknorris.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: ApiService = retrofit.create<ApiService>(ApiService::class.java)

        val call: Call<List<String>> = service.getCategories()

        call.enqueue(object : Callback<List<String>> {
            override fun onResponse(response: Response<List<String>>?, retrofit: Retrofit?) {
                if(response!!.isSuccess){
                    val categories: List<String> = response.body() as List<String>
                    callback(categories)
                }
            }

            override fun onFailure(t: Throwable?) {
                Toast.makeText(context, "Request Fail", Toast.LENGTH_SHORT).show()
            }
        })
    }
}