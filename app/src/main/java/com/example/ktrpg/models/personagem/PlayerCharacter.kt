package com.example.ktrpg.models.personagem

import com.example.ktrpg.Classes
import com.example.ktrpg.Raca

data class PlayerCharacter(
    var name: String = "Jogador",
    var characterClass: Classes? = null,
    var race: Raca? = null,
    var level: Int = 1,
    var xp: Int = 0,
    var xpToNextLevel: Int = 100,
    var maxHp: Int = 100,
    var currentHp: Int = 100,
    var isDefending: Boolean = false
) {
    fun gainXp(amount: Int): Boolean {
        xp += amount
        if (xp >= xpToNextLevel) {
            levelUp()
            return true // Indica que o jogador subiu de nível
        }
        return false
    }

    private fun levelUp() {
        level++
        xp -= xpToNextLevel
        xpToNextLevel = (xpToNextLevel * 1.5).toInt()

        // Melhora os status
        maxHp += 20
        currentHp = maxHp
    }
}
