package com.example.englishmvp.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "progress")

class ProgressStore(private val context: Context) {
    private val xpKey = intPreferencesKey("xp")

    val xp: Flow<Int> = context.dataStore.data.map { it[xpKey] ?: 0 }

    fun completedKey(lessonId: String) = intPreferencesKey("completed_$lessonId")

    fun isCompleted(lessonId: String): Flow<Boolean> =
        context.dataStore.data.map { (it[completedKey(lessonId)] ?: 0) == 1 }

    suspend fun completeLesson(lessonId: String, gainedXp: Int) {
        context.dataStore.edit { prefs ->
            prefs[completedKey(lessonId)] = 1
            prefs[xpKey] = (prefs[xpKey] ?: 0) + gainedXp
        }
    }
}
