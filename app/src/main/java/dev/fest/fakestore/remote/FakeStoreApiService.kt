package dev.fest.fakestore.remote

import android.database.Observable
import dev.fest.fakestore.dto.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FakeStoreApiService {
    @GET("products")
    fun getAllProducts(): Call<List<Product>>
}