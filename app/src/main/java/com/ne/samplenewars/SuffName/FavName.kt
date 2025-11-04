package com.ne.samplenewars.SuffName


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_names")
data class FavName(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String
)
