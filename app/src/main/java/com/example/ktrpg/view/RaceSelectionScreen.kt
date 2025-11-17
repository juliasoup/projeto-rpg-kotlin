package com.example.ktrpg.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ktrpg.models.GameData

@Composable
fun RaceSelectionScreen(onRaceSelected: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Escolha sua Raça", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(32.dp))

        GameData.racas.forEach { raca ->
            Button(onClick = { onRaceSelected(raca.javaClass.simpleName) }) {
                Text(raca.javaClass.simpleName)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
