package com.example.ktrpg.battle

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ktrpg.models.personagem.PlayerCharacter
import kotlin.random.Random

enum class BattleTurn {
    PLAYER_CHOICE, // Jogador escolhe Acar ou Defender
    ENEMY_TURN     // Inimigo ataca
}

class BattleViewModel : ViewModel() {

    // O personagem do jogador será passado para o ViewModel
    var player: PlayerCharacter = PlayerCharacter()

    val enemyHp = mutableStateOf(100)
    val battleLog = mutableStateOf(listOf<String>())
    val turn = mutableStateOf(BattleTurn.PLAYER_CHOICE)
    val isBattleOver = mutableStateOf(false)

    fun startNewBattle(playerCharacter: PlayerCharacter) {
        this.player = playerCharacter
        this.player.currentHp = player.maxHp // Recupera a vida no início de cada batalha
        this.player.isDefending = false

        enemyHp.value = 100
        battleLog.value = listOf("Um novo inimigo apareceu! Nível: ${player.level}")
        turn.value = BattleTurn.PLAYER_CHOICE
        isBattleOver.value = false
    }

    fun playerAction(attack: Boolean) {
        if (turn.value != BattleTurn.PLAYER_CHOICE || isBattleOver.value) return

        if (attack) {
            val playerDamage = Random.nextInt(10, 20) + (player.level * 2)
            enemyHp.value = (enemyHp.value - playerDamage).coerceAtLeast(0)
            addToLog("Você ataca e causa $playerDamage de dano.")
        } else {
            player.isDefending = true
            addToLog("Você se prepara para defender.")
        }

        if (enemyHp.value <= 0) {
            endBattle(victory = true)
        } else {
            turn.value = BattleTurn.ENEMY_TURN
            enemyTurn()
        }
    }

    private fun enemyTurn() {
        if (isBattleOver.value) return

        addToLog("O inimigo se prepara para atacar!")

        val enemyDamage = Random.nextInt(15, 25)
        val finalDamage = if (player.isDefending) (enemyDamage / 2) else enemyDamage

        player.currentHp = (player.currentHp - finalDamage).coerceAtLeast(0)
        addToLog("O inimigo ataca e causa $finalDamage de dano.")

        // Reseta a defesa do jogador para o próximo turno
        player.isDefending = false

        if (player.currentHp <= 0) {
            endBattle(victory = false)
        } else {
            turn.value = BattleTurn.PLAYER_CHOICE
            addToLog("Sua vez! O que você faz?")
        }
    }

    private fun endBattle(victory: Boolean) {
        isBattleOver.value = true
        if (victory) {
            val xpGained = 30 + (player.level * 5)
            addToLog("Você venceu! Você ganhou $xpGained de XP.")
            player.gainXp(xpGained)
        } else {
            addToLog("Você foi derrotado!")
        }
    }

    private fun addToLog(message: String) {
        battleLog.value = battleLog.value + message
    }
}
