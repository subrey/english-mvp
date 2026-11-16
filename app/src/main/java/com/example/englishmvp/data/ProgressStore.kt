package com.example.englishmvp.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "progress")

class ProgressStore(private val context: Context) {
    private val xpKey = intPreferencesKey("xp")
    private val streakKey = intPreferencesKey("streak")
    private val lastActiveKey = longPreferencesKey("last_active_epoch_day")

    val xp: Flow<Int> = context.dataStore.data.map { it[xpKey] ?: 0 }

    /** Racha vigente: 0 si la última actividad fue antes de ayer. */
    val streak: Flow<Int> = context.dataStore.data.map { prefs ->
        val today = epochDayToday()
        val last = prefs[lastActiveKey] ?: -1L
        val count = prefs[streakKey] ?: 0
        if (count > 0 && last >= today - 1) count else 0
    }

    fun completedKey(lessonId: String) = intPreferencesKey("completed_$lessonId")

    fun isCompleted(lessonId: String): Flow<Boolean> =
        context.dataStore.data.map { (it[completedKey(lessonId)] ?: 0) == 1 }

    suspend fun completeLesson(lessonId: String, gainedXp: Int) {
        val today = epochDayToday()
        context.dataStore.edit { prefs ->
            prefs[completedKey(lessonId)] = 1
            prefs[xpKey] = (prefs[xpKey] ?: 0) + gainedXp
            val last = prefs[lastActiveKey] ?: -1L
            val count = prefs[streakKey] ?: 0
            prefs[streakKey] = when (last) {
                today -> count.coerceAtLeast(1) // ya activó hoy
                today - 1 -> count + 1          // ayer también: sube la racha
                else -> 1                        // racha nueva
            }
            prefs[lastActiveKey] = today
        }
    }

    private fun epochDayToday(): Long = System.currentTimeMillis() / 86_400_000L
}
