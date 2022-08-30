package com.example.weather_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kotlin.time.Duration.Companion.milliseconds

private val CARD_WIDTH = 300.dp

@Composable
fun MainDisplay(viewModel: MainViewModel?, list: List<WeatherCard>) {
    Box(modifier = Modifier.background(Color(238, 238, 238))) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight()
        )
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                Spacer(modifier = Modifier.height(0.dp)) // 300 iq move
                HowIsTheWeatherText()
                DisplaySearchLocation(viewModel)
                list.forEach {
                    DisplayWeatherCard(card = it, viewModel)
                }
            }
            DisplayLogo(resource = R.drawable.ic_alster)
        }
    }
}

@Composable
fun HowIsTheWeatherText() {
    Text(
        text = "Hur är vädret i...",
        modifier = Modifier.fillMaxWidth(),
        fontFamily = FontFamily(Font(R.font.roboto_bold, FontWeight.Bold)),
        fontSize = 30.sp,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun DisplaySearchLocation(viewModel: MainViewModel?) {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .size(CARD_WIDTH, 50.dp)
            .clip(shape = RoundedCornerShape(20))
            .background(Color.White)
    ) {
        Row(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(0.9f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) { // Search Component

            var text by remember { mutableStateOf(TextFieldValue("")) }

            Box {
                Column {
                    Row{
                        val textSize = 14.sp
                        Text("Plats: ",
                            fontSize = textSize,
                            fontFamily = FontFamily(Font(R.font.roboto_bold, FontWeight.Bold)))
                        BasicTextField(
                            value = text.text,
                            onValueChange = { str: String -> text = TextFieldValue(str) },
                            textStyle = TextStyle(fontSize = textSize),
                            modifier = Modifier.width(110.dp),
                            singleLine = true
                        )
                    }
                }

            }

            IconButton(
                onClick = { viewModel?.onClickAddLocation(text.text) },
                modifier = Modifier.size(30.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add_location),
                    contentDescription = null
                )
            }
        }
    }
}

fun Int.toTemperatureString() : String{
    return "$this°"
}

@Composable
fun DisplayWeatherCard(card: WeatherCard, viewModel: MainViewModel?) {
    val color = when {
        card.isRaining -> Color(45, 155, 240)
        card.temperature <= 0 -> Color(45, 155, 240)
        card.temperature < 20 -> Color(250, 199, 16)
        else -> Color(242, 71, 38)
    }

    Box(modifier = Modifier
        .background(color)
        .width(CARD_WIDTH)
        .height(100.dp)) {

        Column(modifier = Modifier.fillMaxWidth()) {
            Row(horizontalArrangement = Arrangement.End
                , modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()) {
                IconButton(onClick = { viewModel?.onClickRemoveWeatherCard(card) }, modifier = Modifier.size(30.dp)) {
                    Image(painter = painterResource(id = R.drawable.ic_close_button), contentDescription = "")
                }
            }
        }


        Column (verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
                .padding(13.dp)) {
            Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                Image(
                    painter = painterResource(
                        id = WeatherIconMapper.map(card.weatherIcon) ?: R.drawable.property_1_plus
                    ),
                    modifier = Modifier.size(45.dp),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.size(15.dp))
                Column (modifier = Modifier.weight(1f)) {
                    Text(text = card.temperature.toTemperatureString(), fontSize = 30.sp, color = Color.White, fontFamily = FontFamily(Font(R.font.roboto_bold, FontWeight.Bold)))
                    Text(text = card.location, color = Color.White, fontFamily = FontFamily(Font(R.font.roboto_bold, FontWeight.Bold)),)
                }
            }
        }
    }
}

@Composable
fun DisplayLogo(resource: Int) {
    Box {
        Image(
            painter = painterResource(id = resource),
            contentDescription = "",
            Modifier.size(50.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainDisplay(null, listOf(WeatherCard(19, "Stockholm", false, 113)))
}