package com.example.ktrpg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.ktrpg.battle.BattleScreen
import com.example.ktrpg.ui.theme.KtrpgTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KtrpgTheme {
                // Scaffold fornece a estrutura básica de layout do Material Design
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(modifier = Modifier.fillMaxSize()) {
                        // A nova tela de batalha agora é o conteúdo principal
                        BattleScreen()
                    }
                }
            }
        }
    }
}
