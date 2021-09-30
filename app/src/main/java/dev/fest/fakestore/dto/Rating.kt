package dev.fest.fakestore.dto


import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Rating(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("rate")
    val rate: Double?
)