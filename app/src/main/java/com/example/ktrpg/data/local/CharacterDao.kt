package com.example.ktrpg.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Upsert
    suspend fun upsertCharacter(character: CharacterEntity)

    @Query("SELECT * FROM character_table WHERE id = 1")
    fun getCharacter(): Flow<CharacterEntity?>
}
