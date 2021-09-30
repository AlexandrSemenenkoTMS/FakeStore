package dev.fest.fakestore

import android.app.Application
import androidx.room.Room
import dev.fest.fakestore.db.ProductDatabase

class ProductApp : Application() {
    lateinit var database: ProductDatabase

    override fun onCreate() {
        super.onCreate()
        database =
            Room.databaseBuilder(applicationContext, ProductDatabase::class.java, "ProductDatabase")
                .build()
    }
}

