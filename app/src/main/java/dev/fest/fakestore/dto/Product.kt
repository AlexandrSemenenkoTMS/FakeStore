package dev.fest.fakestore.dto


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.gson.annotations.SerializedName

@Entity
data class Product(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("price")
    val price: Double?,
//    @Relation(parentColumn = "rating", entityColumn = "userId")
    @Embedded(prefix = "rating")
//    @SerializedName("rating")
    val rating: Rating?,
    @SerializedName("title")
    val title: String?
)