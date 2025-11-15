package com.example.ktrpg.battle

import android.app.Service
import android.content.Intent
import android.os.IBinder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BattleService : Service() {

    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.IO + serviceJob)

    override fun onCreate() {
        super.onCreate()
        NotificationHelper.createNotificationChannel(this)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val foregroundNotification = NotificationHelper.createForegroundNotification(this)
        startForeground(1, foregroundNotification) // ID 1 para a notificação de serviço

        serviceScope.launch {
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

    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel()
    }
}
