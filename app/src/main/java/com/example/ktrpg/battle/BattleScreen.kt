package com.example.ktrpg.battle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun BattleScreen() {
    val viewModel: BattleViewModel = viewModel()

    val playerHp by viewModel.playerHp
    val enemyHp by viewModel.enemyHp
    val battleLog by viewModel.battleLog
    val isBattleOver by viewModel.isBattleOver

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Status Bars
        StatusDisplay("Jogador", playerHp, 100, Color.Green)
        Spacer(modifier = Modifier.height(8.dp))
        StatusDisplay("Inimigo", enemyHp, 100, Color.Red)
        Spacer(modifier = Modifier.height(16.dp))

        // Battle Log
        LazyColumn(modifier = Modifier.weight(1f).fillMaxWidth()) {
            items(battleLog) {
                Text(it)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Action Buttons
        if (!isBattleOver) {
            Button(onClick = { viewModel.attack() }, modifier = Modifier.fillMaxWidth()) {
                Text("Atacar")
            }
        } else {
            Button(onClick = { viewModel.resetBattle() }, modifier = Modifier.fillMaxWidth()) {
                Text("Nova Batalha")
            }
        }
    }
}

@Composable
private fun StatusDisplay(name: String, currentHp: Int, maxHp: Int, color: Color) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("$name: $currentHp / $maxHp")
        LinearProgressIndicator(
            progress = { currentHp.toFloat() / maxHp.toFloat() },
            modifier = Modifier.fillMaxWidth(),
            color = color
        )
    }
}
