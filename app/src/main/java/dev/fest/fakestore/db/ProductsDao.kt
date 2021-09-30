package dev.fest.fakestore.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.fest.fakestore.dto.Product
import io.reactivex.Observable

@Dao
interface ProductDao {

    @Insert
    fun insertAllProducts(products: List<Product>)

    @Query("select * from product")
    fun getAllProducts(): List<Product>


}
