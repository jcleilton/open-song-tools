package br.com.jcleilton

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.jcleilton.DAWViewModel
import br.com.jcleilton.*

@Composable
fun ControlBar(viewModel: DAWViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(LogicHeaderBg)
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Toggles da Esquerda (Inspector, Smart Controls)
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            ButtonToggle(
                label = "i", 
                active = viewModel.isInspectorVisible, 
                onClick = { viewModel.isInspectorVisible = !viewModel.isInspectorVisible }
            )
            ButtonToggle(
                label = "Mixer", 
                active = viewModel.isBottomDrawerVisible, 
                onClick = { viewModel.isBottomDrawerVisible = !viewModel.isBottomDrawerVisible }
            )
        }

        // Botoes de Transporte
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TransportButton(
                symbol = "⟲",
                isActive = viewModel.isCycleEnabled,
                activeColor = AccentCycle
            ) { viewModel.isCycleEnabled = !viewModel.isCycleEnabled }

            TransportButton(
                symbol = "■",
                isActive = false,
                activeColor = Color.Gray
            ) { viewModel.isPlaying = false }

            TransportButton(
                symbol = if (viewModel.isPlaying) "⏸" else "▶",
                isActive = viewModel.isPlaying,
                activeColor = AccentPlay
            ) { viewModel.isPlaying = !viewModel.isPlaying }

            TransportButton(
                symbol = "●",
                isActive = viewModel.isRecording,
                activeColor = AccentRecord
            ) { viewModel.isRecording = !viewModel.isRecording }
        }

        // Visor LCD Digital Estilo Logic Pro
        Row(
            modifier = Modifier
                .background(LcdBackground, RoundedCornerShape(6.dp))
                .border(1.dp, LogicBorder, RoundedCornerShape(6.dp))
                .padding(horizontal = 16.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            LcdSection(title = "BARS", value = "0001.1.1.000")
            LcdSection(title = "TEMPO", value = "${viewModel.bpm} BPM")
            LcdSection(title = "KEY", value = viewModel.keySignature)
            LcdSection(title = "SIGNATURE", value = viewModel.timeSignature)
        }

        // Medidor de Recursos
        Column(horizontalAlignment = Alignment.End) {
            Text("CPU", color = Color.Gray, fontSize = 9.sp)
            LinearProgressIndicator(
                progress = { 0.18f },
                modifier = Modifier.width(50.dp).height(4.dp),
                color = LcdTextGreen,
                trackColor = Color.DarkGray
            )
        }
    }
}

@Composable
private fun TransportButton(
    symbol: String,
    isActive: Boolean,
    activeColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(36.dp)
            .background(
                if (isActive) activeColor else LogicPanelBg,
                RoundedCornerShape(6.dp)
            )
            .border(1.dp, LogicBorder, RoundedCornerShape(6.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = symbol,
            color = if (isActive) Color.Black else Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ButtonToggle(label: String, active: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .height(32.dp)
            .padding(horizontal = 8.dp)
            .background(if (active) TrackBlue else LogicPanelBg, RoundedCornerShape(4.dp))
            .clickable { onClick() }
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = label, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun LcdSection(title: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = title, color = Color.Gray, fontSize = 8.sp, fontWeight = FontWeight.Bold)
        Text(
            text = value,
            color = LcdTextCyan,
            fontSize = 13.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        )
    }
}