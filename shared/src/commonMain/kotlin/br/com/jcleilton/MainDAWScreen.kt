package br.com.jcleilton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import br.com.jcleilton.BottomTab
import br.com.jcleilton.DAWViewModel
import br.com.jcleilton.*
import br.com.jcleilton.LogicDarkBg

@Composable
fun MainDAWScreen() {
    val viewModel = remember { DAWViewModel() }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = LogicDarkBg
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // 1. Barra de Controle Superior / Transport Bar
            ControlBar(viewModel = viewModel)

            // 2. Área Central (Inspector + Arrangement Grid)
            Row(modifier = Modifier.weight(1f).fillMaxWidth()) {
                // Painel Inspector Esquerdo (Retrátil)
                if (viewModel.isInspectorVisible) {
                    val currentTrack = viewModel.tracks.find { it.id == viewModel.selectedTrackId }
                    currentTrack?.let { track ->
                        Box(modifier = Modifier.fillMaxHeight()) {
                            ChannelStrip(track = track)
                        }
                    }
                }

                // Grid do Arranjador / Timeline
                ArrangementView(
                    viewModel = viewModel,
                    modifier = Modifier.weight(1f).fillMaxHeight()
                )
            }

            // 3. Gaveta Inferior (Mixer / Piano Roll / Smart Controls)
            if (viewModel.isBottomDrawerVisible) {
                when (viewModel.activeBottomTab) {
                    BottomTab.MIXER -> MixerConsole(viewModel = viewModel)
                    BottomTab.PIANO_ROLL -> { /* Implementação do Piano Roll MIDI */ }
                    BottomTab.SMART_CONTROLS -> { /* Controles Macro */ }
                }
            }
        }
    }
}