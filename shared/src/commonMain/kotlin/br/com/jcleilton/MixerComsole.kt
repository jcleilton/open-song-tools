package br.com.jcleilton

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.jcleilton.DAWViewModel
import br.com.jcleilton.Track
import br.com.jcleilton.*

@Composable
fun MixerConsole(viewModel: DAWViewModel, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .height(260.dp)
            .background(LogicHeaderBg)
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(viewModel.tracks) { track ->
            ChannelStrip(track = track)
        }
    }
}

@Composable
internal fun ChannelStrip(track: Track) {
    Column(
        modifier = Modifier
            .width(90.dp)
            .fillMaxHeight()
            .background(LogicPanelBg, RoundedCornerShape(6.dp))
            .border(1.dp, LogicBorder, RoundedCornerShape(6.dp))
            .padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Nome da Trilha
        Text(
            text = track.name,
            color = Color.White,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Slots de Efeitos (FX)
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            FxSlot("EQ Kit")
            FxSlot("Compressor")
            FxSlot("Space Dly")
        }

        Spacer(modifier = Modifier.weight(1f))

        // Peak Meter & Fader de Volume
        Row(
            modifier = Modifier.height(110.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Fader
            Slider(
                value = track.volumeDb,
                onValueChange = { track.volumeDb = it },
                valueRange = -40f..6f,
                modifier = Modifier.width(90.dp),
                colors = SliderDefaults.colors(
                    thumbColor = Color.LightGray,
                    activeTrackColor = track.color
                )
            )
        }

        Spacer(modifier = Modifier.height(6.dp))
        
        // Display de Volume em dB
        Text(
            text = "${track.volumeDb.toInt()} dB",
            color = LcdTextGreen,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun FxSlot(name: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(18.dp)
            .background(LogicDarkBg, RoundedCornerShape(2.dp))
            .border(0.5.dp, LogicBorder, RoundedCornerShape(2.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = name, color = LcdTextCyan, fontSize = 9.sp)
    }
}