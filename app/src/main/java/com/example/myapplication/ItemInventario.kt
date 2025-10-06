package com.exemplo.inventario.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inventario")
data class ItemInventario(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        val nome: String,
        val quantidade: Int,
        val raridade: String // Ex: "Comum", "Raro", "Épico"
)
