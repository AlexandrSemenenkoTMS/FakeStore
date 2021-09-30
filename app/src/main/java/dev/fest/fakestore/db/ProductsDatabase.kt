package dev.fest.fakestore.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.fest.fakestore.dto.Product

@Database(entities = [Product::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun getProductDao(): ProductDao
}

