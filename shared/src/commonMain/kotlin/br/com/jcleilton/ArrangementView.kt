package br.com.jcleilton

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.jcleilton.AudioRegion
import br.com.jcleilton.DAWViewModel
import br.com.jcleilton.Track
import br.com.jcleilton.AccentRecord
import br.com.jcleilton.LogicBorder
import br.com.jcleilton.LogicDarkBg
import br.com.jcleilton.LogicHeaderBg
import br.com.jcleilton.LogicPanelBg

@Composable
fun ArrangementView(viewModel: DAWViewModel, modifier: Modifier = Modifier) {
    Column(modifier = modifier.background(LogicDarkBg)) {
        // Régua de Tempo / Compassos
        RulerHeader()

        // Lista de Faixas e Grade de Regiões
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn {
                items<Track>(items = viewModel.tracks, key = { it.id }) { track ->
                    TrackRow(
                        track = track,
                        isSelected = track.id == viewModel.selectedTrackId,
                        onSelect = { viewModel.selectedTrackId = track.id }
                    )
                }
            }

            // Agulha de Reprodução (Playhead)
            Canvas(modifier = Modifier.fillMaxSize()) {
                val xPosition: Float = (viewModel.currentPositionBeat * 30f) + 200f
                drawLine(
                    color = AccentRecord,
                    start = Offset(x = xPosition, y = 0f),
                    end = Offset(x = xPosition, y = size.height),
                    strokeWidth = 2f
                )
            }
        }
    }
}

@Composable
private fun RulerHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(28.dp)
            .background(LogicHeaderBg)
            .border(0.5.dp, LogicBorder)
    ) {
        // Cabeçalho alinhado à largura das faixas (200.dp)
        Box(
            modifier = Modifier
                .width(200.dp)
                .fillMaxHeight()
                .border(0.5.dp, LogicBorder)
        )

        // Marcadores de Compasso
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            (1..32).forEach { bar ->
                Box(
                    modifier = Modifier.width(60.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = bar.toString(),
                        color = Color.Gray,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun TrackRow(
    track: Track,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .border(0.5.dp, LogicBorder)
            .background(if (isSelected) LogicPanelBg else LogicDarkBg)
            .clickable { onSelect() }
    ) {
        // Cabeçalho da Trilha
        Row(
            modifier = Modifier
                .width(200.dp)
                .fillMaxHeight()
                .background(LogicHeaderBg)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .fillMaxHeight()
                    .background(track.color, RoundedCornerShape(2.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = track.name,
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = track.typeName,
                    color = Color.Gray,
                    fontSize = 10.sp
                )
            }

            // Botoes Mute e Solo
            Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                MiniButton(
                    text = "M",
                    active = track.isMuted,
                    activeColor = Color(0xFFFFCC00),
                    onClick = { track.isMuted = !track.isMuted }
                )
                MiniButton(
                    text = "S",
                    active = track.isSoloed,
                    activeColor = Color(0xFF30D158),
                    onClick = { track.isSoloed = !track.isSoloed }
                )
            }
        }

        // Grade de Regiões de Áudio / MIDI com Tipagem de Dp Explícita
        Box(modifier = Modifier.fillMaxSize()) {
            track.regions.forEach { region: AudioRegion ->
                val startPx: Dp = (region.startBeat * 15).dp
                val widthPx: Dp = (region.durationBeats * 15).dp

                Box(
                    modifier = Modifier
                        .offset(x = startPx)
                        .width(widthPx)
                        .fillMaxHeight()
                        .padding(vertical = 4.dp)
                        .background(
                            color = region.color.copy(alpha = 0.8f),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = region.color,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(6.dp)
                ) {
                    Text(
                        text = region.name,
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
private fun MiniButton(
    text: String,
    active: Boolean,
    activeColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(20.dp)
            .background(
                color = if (active) activeColor else LogicDarkBg,
                shape = RoundedCornerShape(3.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (active) Color.Black else Color.Gray,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )
    }
}