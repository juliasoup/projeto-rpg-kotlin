package com.exemplo.inventario.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exemplo.inventario.data.InventarioRepository
import com.exemplo.inventario.data.ItemInventario
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class InventarioViewModel(private val repository: InventarioRepository) : ViewModel() {

    val todosOsItens = repository.todosOsItens
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun adicionarItem(nome: String, quantidade: Int, raridade: String) {
        viewModelScope.launch {
            repository.adicionarItem(ItemInventario(nome = nome, quantidade = quantidade, raridade = raridade))
        }
    }
}
