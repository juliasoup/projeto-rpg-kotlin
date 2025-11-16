package com.example.ktrpg.battle

import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Build
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BattleService : LifecycleService() {

    override fun onCreate() {
        super.onCreate()
        NotificationHelper.createNotificationChannel(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        val foregroundNotification = NotificationHelper.createForegroundNotification(this)

        // A partir do Android 14, é obrigatório fornecer o tipo do serviço
        // que foi declarado no Manifest.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            startForeground(1, foregroundNotification, ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC) // << MUDANÇA AQUI
        } else {
            startForeground(1, foregroundNotification)
        }

        lifecycleScope.launch {
            startBattleSimulation()
        }

        return START_NOT_STICKY
    }

    private suspend fun startBattleSimulation() {
        // Simula uma batalha que dura 10 segundos
        delay(10000)

        // Simula a morte do personagem
        NotificationHelper.showCharacterDiedNotification(this)

        // Para o serviço após a conclusão
        stopSelf()
    }
}
