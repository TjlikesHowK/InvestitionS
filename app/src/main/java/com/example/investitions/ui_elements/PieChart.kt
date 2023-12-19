package com.example.investitions.ui_elements

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PieChart(
    data: Map<String, Int>,
    radiusOuter: Dp = 90.dp,
    chartBaeWidth: Dp = 20.dp,
    animDuration: Int = 1000,
    paddingTop: Int
){
    val sum = data.values.sum()
    val floatValue = mutableListOf<Float>()

    data.values.forEachIndexed{ index, value ->
        floatValue.add(index, 360*value.toFloat() / sum.toFloat())
    }

    val colors = listOf(
        Color(0xFF425FFF),
        Color(0xFFFF4249),
        Color(0xFFFFF249),
        Color(0xFF424959),
        Color(0xFF55B249),
        Color(0xFFFB427F),
        Color(0xFFFF4FFF),
        Color(0xFFFF4F1F),
        Color(0x1FBF4FFF),
        Color(0xFFFAFA9F),
        Color(0xFF0FFFFF),
        Color(0xFF0FFFFF),
        Color(0xFF0444FF),
        Color(0xFFF111FF),
        Color(0xFFFFF11F),
        Color(0xFF0FF11F),
        Color(0xFF0F555F),
        Color(0xFFAAFA1F),
        Color(0xFFAA1111),
        Color(0xFF11211F)
    )

    var animationPlayed by remember { mutableStateOf(false) }
    var lastValue = 0f

    val animatedSize by animateFloatAsState(
        targetValue = if (animationPlayed) radiusOuter.value*2f else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        ), label = ""
    )

    val animateRotation by animateFloatAsState(
        targetValue = if (animationPlayed) 90f*11f else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        ), label = ""
    )

    LaunchedEffect(key1 = true){
        animationPlayed = true
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = paddingTop.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier
                .size(animatedSize.dp),
            contentAlignment = Alignment.Center
        ){
            Canvas(
                modifier = Modifier
                    .size(radiusOuter * 2f)
                    .rotate(animateRotation)
            ){
                floatValue.forEachIndexed{index, value ->
                    drawArc(
                        color = colors[index],
                        lastValue,
                        value,
                        useCenter = false,
                        style = Stroke(chartBaeWidth.toPx(),
                            cap = StrokeCap.Butt)
                    )
                    lastValue += value
                }
            }
        }
        DetailsPieChart(
            data = data,
            colors = colors
        )
    }
}

@Composable
fun DetailsPieChart(
    data: Map<String, Int>,
    colors: List<Color>
) {
    Column(
        modifier = Modifier
            .padding(top = 80.dp)
            .fillMaxWidth()
    ) {
        data.values.forEachIndexed { index, value ->
            DetailsPieChartItem(
                data = Pair(data.keys.elementAt(index), value),
                color = colors[index]
            )
        }

    }
}

@Composable
fun DetailsPieChartItem(
    data: Pair<String, Int>,
    height: Dp = 25.dp,
    color: Color
) {

    Surface(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 20.dp),
        color = Color.Transparent
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .background(
                        color = color,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .size(height)
            )

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = data.first,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = data.second.toString(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }
    }
}