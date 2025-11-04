package com.ne.samplenewars

import androidx.room.*
import com.ne.samplenewars.SuffName.SuffName

@Dao
interface SuffDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSuffName(name: SuffName)

    @Query("SELECT * FROM suff_names")
    suspend fun getAllSuffNames(): List<SuffName>
}
