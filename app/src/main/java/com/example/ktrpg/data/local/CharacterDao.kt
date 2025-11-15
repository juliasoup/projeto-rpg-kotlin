package com.example.ktrpg.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: Character)

    @Query("SELECT * FROM characters WHERE id = :id")
    fun getCharacter(id: Int): Flow<Character?>

    @Query("SELECT * FROM characters")
    fun getAllCharacters(): Flow<List<Character>>
}
