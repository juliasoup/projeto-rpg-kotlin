package com.exemplo.inventario.data

import kotlinx.coroutines.flow.Flow

class InventarioRepository(private val itemDao: ItemDao) {

    val todosOsItens: Flow<List<ItemInventario>> = itemDao.buscarTodos()

    suspend fun adicionarItem(item: ItemInventario) {
        itemDao.inserir(item)
    }

    suspend fun atualizarItem(item: ItemInventario) {
        itemDao.atualizar(item)
    }
}
