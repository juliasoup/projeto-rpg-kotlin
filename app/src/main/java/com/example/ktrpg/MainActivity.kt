package com.example.ktrpg

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ktrpg.battle.BattleScreen
import com.example.ktrpg.controller.GameViewModel
import com.example.ktrpg.controller.GameState
import com.example.ktrpg.controller.ViewModelFactory
import com.example.ktrpg.ui.theme.KtrpgTheme
import com.example.ktrpg.view.CharacterSheetScreen
import com.example.ktrpg.view.ClassSelectionScreen
import com.example.ktrpg.view.RaceSelectionScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val factory = ViewModelFactory(application as Application)
            val gameViewModel: GameViewModel = viewModel(factory = factory)
            val gameState by gameViewModel.gameState.collectAsState()
            val player by gameViewModel.player.collectAsState()

            KtrpgTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        when (gameState) {
                            GameState.LOADING -> {
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                    CircularProgressIndicator()
                                }
                            }
                            GameState.CLASS_SELECTION -> {
                                ClassSelectionScreen(onClassSelected = { gameViewModel.selectClass(it) })
                            }
                            GameState.RACE_SELECTION -> {
                                RaceSelectionScreen(onRaceSelected = { gameViewModel.selectRace(it) })
                            }
                            GameState.CHARACTER_SHEET -> {
                                CharacterSheetScreen(
                                    player = player,
                                    onStartBattle = { gameViewModel.goToBattle() }
                                )
                            }
                            GameState.BATTLE -> {
                                BattleScreen(
                                    player = player,
                                    onBattleEnd = { xpGained -> gameViewModel.endBattleAndGainXp(xpGained) },
                                    onPlayerDied = { gameViewModel.returnToClassSelection() }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
