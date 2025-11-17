package com.example.ktrpg.battle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ktrpg.models.personagem.PlayerCharacter

@Composable
fun BattleScreen(player: PlayerCharacter, onBattleEnd: (xpGained: Int) -> Unit, onPlayerDied: () -> Unit) {
    val viewModel: BattleViewModel = viewModel()

    // Inicia a batalha na primeira vez que a tela é composta
    LaunchedEffect(key1 = player) {
        viewModel.startNewBattle(player)
    }

    val enemyHp by viewModel.enemyHp
    val battleLog by viewModel.battleLog
    val currentTurn by viewModel.turn
    val isBattleOver by viewModel.isBattleOver

    val listState = rememberLazyListState()
    // Rola o log para o final sempre que uma nova mensagem é adicionada
    LaunchedEffect(battleLog.size) {
        if (battleLog.isNotEmpty()) {
            listState.animateScrollToItem(battleLog.lastIndex)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Status
        StatusDisplay("Jogador (Lvl ${player.level})", player.currentHp, player.maxHp, Color.Green)
        Spacer(modifier = Modifier.height(8.dp))
        StatusDisplay("Inimigo", enemyHp, 100, Color.Red)
        Spacer(modifier = Modifier.height(16.dp))

        // Log de Batalha
        LazyColumn(state = listState, modifier = Modifier.weight(1f).fillMaxWidth()) {
            items(battleLog) {
                Text(it)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Ações
        if (isBattleOver) {
            if (player.currentHp > 0) {
                val xpGained = 30 + (player.level * 5)
                Button(onClick = { onBattleEnd(xpGained) }, modifier = Modifier.fillMaxWidth()) {
                    Text("Próxima Batalha")
                }
            } else {
                Button(onClick = onPlayerDied, modifier = Modifier.fillMaxWidth()) {
                    Text("Voltar ao Início")
                }
            }
        } else {
            when (currentTurn) {
                BattleTurn.PLAYER_CHOICE -> {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        Button(onClick = { viewModel.playerAction(attack = true) }) {
                            Text("Atacar")
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(onClick = { viewModel.playerAction(attack = false) }) {
                            Text("Defender")
                        }
                    }
                }
                BattleTurn.ENEMY_TURN -> {
                    Text("Turno do Inimigo...")
                }
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
