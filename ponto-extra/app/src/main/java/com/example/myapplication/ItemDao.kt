package com.exemplo.inventario.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(item: ItemInventario)

    @Query("SELECT * FROM inventario ORDER BY nome ASC")
    fun buscarTodos(): Flow<List<ItemInventario>>

    @Update
    suspend fun atualizar(item: ItemInventario)
}
