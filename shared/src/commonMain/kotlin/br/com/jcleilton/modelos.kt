package br.com.jcleilton

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import br.com.jcleilton.*

enum class BottomTab { MIXER, PIANO_ROLL, SMART_CONTROLS }

data class AudioRegion(
    val id: String,
    val name: String,
    val startBeat: Float,
    val durationBeats: Float,
    val color: Color
)

data class Track(
    val id: String,
    val name: String,
    val typeName: String,
    val color: Color,
    var isMuted: Boolean = false,
    var isSoloed: Boolean = false,
    var isArmed: Boolean = false,
    var volumeDb: Float = 0f,
    var pan: Float = 0f,
    val regions: List<AudioRegion> = emptyList()
)

class DAWViewModel {
    var isPlaying by mutableStateOf(false)
    var isRecording by mutableStateOf(false)
    var isCycleEnabled by mutableStateOf(true)
    
    var bpm by mutableStateOf(120)
    var keySignature by mutableStateOf("C maj")
    var timeSignature by mutableStateOf("4/4")
    var currentPositionBeat by mutableFloatStateOf(1.0f)
    
    var isInspectorVisible by mutableStateOf(true)
    var isBottomDrawerVisible by mutableStateOf(true)
    var activeBottomTab by mutableStateOf(BottomTab.MIXER)
    
    var selectedTrackId by mutableStateOf("t1")

    val tracks = mutableStateListOf(
        Track(
            id = "t1",
            name = "Analog Synth",
            typeName = "Software Instrument",
            color = TrackBlue,
            regions = listOf(
                AudioRegion("r1", "Chords Synth", 1f, 8f, TrackBlue),
                AudioRegion("r2", "Lead Arp", 9f, 8f, TrackBlue)
            )
        ),
        Track(
            id = "t2",
            name = "808 Bass Sub",
            typeName = "Software Instrument",
            color = TrackOrange,
            regions = listOf(
                AudioRegion("r3", "Sub Bass Loop", 1f, 16f, TrackOrange)
            )
        ),
        Track(
            id = "t3",
            name = "Beat Kit",
            typeName = "Drum Kit",
            color = TrackGreen,
            regions = listOf(
                AudioRegion("r4", "Drum Pattern", 1f, 16f, TrackGreen)
            )
        ),
        Track(
            id = "t4",
            name = "Vox Lead",
            typeName = "Audio Track",
            color = TrackPurple,
            regions = listOf(
                AudioRegion("r5", "Main Vocal Takes", 5f, 10f, TrackPurple)
            )
        )
    )
}