package com.example.ktrpg.controller

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktrpg.data.CharacterRepository
import com.example.ktrpg.data.local.CharacterEntity
import com.example.ktrpg.models.GameData
import com.example.ktrpg.models.personagem.PlayerCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

enum class GameState {
    LOADING,
    CLASS_SELECTION,
    RACE_SELECTION,
    CHARACTER_SHEET,
    BATTLE
}

class GameViewModel(private val repository: CharacterRepository) : ViewModel() {

    private val _gameState = MutableStateFlow(GameState.LOADING)
    val gameState = _gameState.asStateFlow()

    private val _player = MutableStateFlow(PlayerCharacter())
    val player = _player.asStateFlow()

    init {
        loadCharacter()
    }

    private fun loadCharacter() {
        viewModelScope.launch {
            val entity = repository.getCharacter().first()
            if (entity != null) {
                _player.value = PlayerCharacter(
                    name = entity.name,
                    characterClass = GameData.getClasse(entity.characterClassName),
                    race = GameData.getRaca(entity.raceName),
                    level = entity.level,
                    xp = entity.xp,
                    xpToNextLevel = entity.xpToNextLevel,
                    maxHp = entity.maxHp,
                    currentHp = entity.maxHp // Sempre começa com vida cheia
                )
                _gameState.value = GameState.CHARACTER_SHEET
            } else {
                _gameState.value = GameState.CLASS_SELECTION
            }
        }
    }

    fun selectClass(className: String) {
        val characterClass = GameData.getClasse(className)!!
        _player.value = _player.value.copy(characterClass = characterClass)
        _gameState.value = GameState.RACE_SELECTION
    }

    fun selectRace(raceName: String) {
        val race = GameData.getRaca(raceName)!!
        _player.value = _player.value.copy(race = race)
        savePlayerState() // Salva pela primeira vez
        _gameState.value = GameState.CHARACTER_SHEET
    }

    fun goToBattle() {
        _gameState.value = GameState.BATTLE
    }

    fun endBattleAndGainXp(xpGained: Int) {
        _player.value.gainXp(xpGained)
        savePlayerState()
        _gameState.value = GameState.CHARACTER_SHEET
    }

    fun returnToClassSelection() {
        viewModelScope.launch {
            // Deleta o personagem do banco de dados
            val entity = toEntity(_player.value)
            // Para deletar, precisamos de uma função no DAO, mas por enquanto, vamos apenas resetar.
            // Idealmente: repository.deleteCharacter(entity)
            _player.value = PlayerCharacter()
            _gameState.value = GameState.CLASS_SELECTION
        }
    }

    private fun savePlayerState() {
        viewModelScope.launch {
            val entity = toEntity(_player.value)
            repository.saveCharacter(entity)
        }
    }

    private fun toEntity(player: PlayerCharacter): CharacterEntity {
        return CharacterEntity(
            name = player.name,
            characterClassName = player.characterClass!!.nome,
            raceName = player.race!!.javaClass.simpleName,
            level = player.level,
            xp = player.xp,
            xpToNextLevel = player.xpToNextLevel,
            maxHp = player.maxHp
        )
    }
}
