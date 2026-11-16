package com.example.englishmvp.data

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.Locale

class TtsHelper(context: Context) {
    private var tts: TextToSpeech? = null
    private var ready = false

    init {
        tts = TextToSpeech(context) { status ->
            ready = status == TextToSpeech.SUCCESS
            if (ready) {
                tts?.language = Locale.US
                tts?.setSpeechRate(0.9f)
            }
        }
    }

    fun speak(text: String) {
        if (ready) tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "english_mvp")
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
        tts = null
    }
}
