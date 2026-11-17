package com.example.englishmvp.data

import android.media.AudioManager
import android.media.ToneGenerator

class SoundHelper {
    private var tone: ToneGenerator? = ToneGenerator(AudioManager.STREAM_MUSIC, 80)

    /** Beep doble de acierto */
    fun success() { tone?.startTone(ToneGenerator.TONE_PROP_ACK, 150) }

    /** Zumbido de error */
    fun error() { tone?.startTone(ToneGenerator.TONE_PROP_NAK, 200) }

    fun release() {
        tone?.release()
        tone = null
    }
}
