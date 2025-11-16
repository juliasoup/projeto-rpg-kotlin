package com.example.ktrpg.battle

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class BattleViewModel : ViewModel() {

    val playerHp = mutableStateOf(100)
    val enemyHp = mutableStateOf(100)
    val battleLog = mutableStateOf(listOf("Um inimigo apareceu!"))
    val isBattleOver = mutableStateOf(false)

    fun attack() {
        if (isBattleOver.value) return

        // Player attacks
        val playerDamage = Random.nextInt(5, 15)
        enemyHp.value = (enemyHp.value - playerDamage).coerceAtLeast(0)
        addToLog("Você ataca e causa $playerDamage de dano.")

        if (enemyHp.value <= 0) {
            addToLog("Você venceu a batalha!")
            isBattleOver.value = true
            return
        }

        // Enemy attacks
        val enemyDamage = Random.nextInt(3, 10)
        playerHp.value = (playerHp.value - enemyDamage).coerceAtLeast(0)
        addToLog("O inimigo ataca e causa $enemyDamage de dano.")

        if (playerHp.value <= 0) {
            addToLog("Você foi derrotado!")
            isBattleOver.value = true
        }
    }

    fun resetBattle() {
        playerHp.value = 100
        enemyHp.value = 100
        battleLog.value = listOf("Um novo inimigo apareceu!")
        isBattleOver.value = false
    }

    private fun addToLog(message: String) {
        battleLog.value = battleLog.value + message
    }
}
