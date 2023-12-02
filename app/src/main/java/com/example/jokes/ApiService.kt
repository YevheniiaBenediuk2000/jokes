package com.example.jokes
import retrofit.Call
import retrofit.http.GET
import retrofit.http.Query

// This interface defines an API
// service for getting random jokes.
interface ApiService {
    // This annotation specifies that the HTTP method
    // is GET and the endpoint URL is "jokes/random".
    @GET("jokes/random")
    // This method returns a Call object with a generic
    // type of DataModel, which represents
    // the data model for the response.
//    fun getjokes(): Call<DataModel>
    fun getjokes(@Query("category") category: String?): Call<DataModel>

    @GET("jokes/categories")
    fun getCategories(): Call<List<String>>

}