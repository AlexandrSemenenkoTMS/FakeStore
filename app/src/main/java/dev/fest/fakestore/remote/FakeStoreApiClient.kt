package dev.fest.fakestore.remote

import io.reactivex.plugins.RxJavaPlugins
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object FakeStoreApiClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com/")
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val fakeStoreApiService = retrofit.create(FakeStoreApiService::class.java)
}