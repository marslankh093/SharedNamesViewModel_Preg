package com.ne.samplenewars

import androidx.room.*
import com.ne.samplenewars.SuffName.FavName

@Dao
interface FavDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavName(name: FavName)

    @Query("SELECT * FROM fav_names")
    suspend fun getAllFavNames(): List<FavName>

    @Query("DELETE FROM fav_names WHERE id = :id")
    suspend fun deleteFavById(id: Long)
}
