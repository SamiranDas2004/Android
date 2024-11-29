package com.example.retrofitecommerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

           Box(contentAlignment = Alignment.Center){
               CirculerProgress(percentage = 1f, number = 100)
           }
        }
    }

// Data class to hold card data
data class CardData(
    val painter: Painter,
    val contentDescription: String,
    val title: String
)

@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(15.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Main Image
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Gradient Overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )
            )

            // Title Text
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = buildAnnotatedString {
                        // Style the first letter
                        withStyle(
                            style = SpanStyle(
                                color = Color.Green,  // Green color for the first letter
                                fontSize = 20.sp      // Optional: Larger font size for the first letter
                            )
                        ) {
                            append(title[0])  // Append the first letter
                        }
                        // Style the rest of the text
                        append(title.substring(1))
                    },
                    style = TextStyle(
                        color = Color.White,  // Default color for the rest of the text
                        fontSize = 16.sp      // Default font size
                    )
                )
            }
        }
    }
}


@Composable
fun ColorBox(modifier: Modifier = Modifier) {
    val color = remember { mutableStateOf(Color.Yellow) }

    Box(
        modifier = modifier
            .background(color.value)
            .clickable {
                color.value = if (color.value == Color.Yellow) Color.Green else Color.Yellow
            }
    ) {
        // Add content that you want to align inside the Box
        Text(
            text = "Aligned Text",
            modifier = Modifier
                .align(Alignment.BottomStart) // Align content inside the Box
                .padding(8.dp),
            color = Color.White
        )


    }
}



@Composable
fun ItemList() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        itemsIndexed(
            listOf(
                "Hi ",
                " my name",
                " is ",
                "sam"
            )
        ) { index, item ->  // Use `index` and `item`
            Text(
                text = item,  // item is the string
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)

            )
        }
    }
}}

@Composable
fun CirculerProgress(
    percentage:Float,
    number: Int,
    fontSize: TextUnit = 28.sp,
    redius: Dp =50.dp,
    color:Color = Color.Green,
    strokeWidth:Dp=8.dp,
    animDuration:Int=5000,
    animDelay: Int =0
    ){
    var animationPlayed by remember { mutableStateOf(false) }
    val curPercentage= animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )
    LaunchedEffect(key1 = true) {
        animationPlayed=true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(redius*2f)
    ){
        Canvas(modifier = Modifier.size(redius*2f)) {
            drawArc(
                color =color,
                startAngle = -90f,
                sweepAngle = 360*curPercentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap= StrokeCap.Round)
            )
        }
        Text(
            text = (curPercentage.value*number).toInt().toString(),
            color=Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}