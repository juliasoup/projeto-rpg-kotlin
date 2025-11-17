package com.example.ktrpg.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_table")
data class CharacterEntity(
    @PrimaryKey
    val id: Int = 1, // Teremos apenas um personagem salvo por enquanto

    val name: String,
    val characterClassName: String,
    val raceName: String,
    val level: Int,
    val xp: Int,
    val xpToNextLevel: Int,
    val maxHp: Int
)
