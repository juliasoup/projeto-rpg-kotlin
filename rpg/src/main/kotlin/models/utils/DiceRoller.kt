package models.utils

import java.security.SecureRandom

object DiceRoller {
    private val random = SecureRandom()

    private fun d6 () = random.nextInt(6) + 1

    fun roll3d6 (): Int = (1..3).sumOf { d6() }

    fun roll4d6DropMen(): Int {
        val rolls = List(4) { d6() }.sortedDescending()
        return rolls.take(3).sum()
    }

}