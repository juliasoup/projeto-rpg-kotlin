package com.example.ktrpg.data

import com.example.ktrpg.data.local.CharacterDao
import com.example.ktrpg.data.local.CharacterEntity
import kotlinx.coroutines.flow.Flow

class CharacterRepository(private val characterDao: CharacterDao) {

    fun getCharacter(): Flow<CharacterEntity?> = characterDao.getCharacter()

    suspend fun saveCharacter(character: CharacterEntity) {
        characterDao.upsertCharacter(character)
    }
}
