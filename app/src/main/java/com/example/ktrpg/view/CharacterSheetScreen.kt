package com.example.ktrpg.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ktrpg.models.personagem.PlayerCharacter

@Composable
fun CharacterSheetScreen(player: PlayerCharacter, onStartBattle: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text("Ficha do Personagem", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        // Informações Básicas
        InfoCard("Informações Básicas") {
            Text("Nome: ${player.name}")
            Text("Nível: ${player.level}")
            Text("XP: ${player.xp} / ${player.xpToNextLevel}")
            Text("HP: ${player.currentHp} / ${player.maxHp}")
        }

        // Classe
        player.characterClass?.let {
            InfoCard("Classe: ${it.nome}") {
                Text(it.exibirInfos(), lineHeight = 20.sp)
            }
        }

        // Raça
        player.race?.let {
            InfoCard("Raça: ${it.javaClass.simpleName}") {
                Text("Movimento: ${it.movimento}")
                Text("Infravisão: ${it.infravisao}m")
                Text("Alinhamento: ${it.alinhamento}")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Habilidades Raciais:", fontWeight = FontWeight.Bold)
                it.habilidades().forEach { habilidade -> // << CORREÇÃO AQUI
                    Text("- $habilidade")
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botão para Batalha
        Button(onClick = onStartBattle, modifier = Modifier.fillMaxWidth()) {
            Text("Procurar Batalha")
        }
    }
}

@Composable
private fun InfoCard(title: String, content: @Composable () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            content()
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}
