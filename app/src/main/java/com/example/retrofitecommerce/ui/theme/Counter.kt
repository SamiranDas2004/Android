package com.example.calculator.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorUI() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {
        var displayText by remember { mutableStateOf("0") }
        var currentOperation by remember { mutableStateOf<String?>(null) }
        var firstOperand by remember { mutableStateOf<Float?>(null) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Display Screen
            DisplayScreen(displayText)

            Spacer(modifier = Modifier.height(6.dp))

            // Button Grid
            ButtonGrid(onButtonClick = { label ->
                // Handle the button click here
                when {
                    label == "=" -> {
                        firstOperand?.let {
                            val result = when (currentOperation) {
                                "+" -> it + displayText.toFloat()
                                "-" -> it - displayText.toFloat()
                                "*" -> it * displayText.toFloat()
                                "/" -> it / displayText.toFloat()
                                else -> displayText.toFloat()
                            }
                            displayText = result.toString()
                            currentOperation = null
                            firstOperand = null
                        }
                    }
                    label == "+" || label == "-" || label == "*" || label == "/" -> {
                        firstOperand = displayText.toFloat()
                        currentOperation = label
                        displayText = "0"
                    }
                    label == "C" -> {
                        displayText = "0"
                        currentOperation = null
                        firstOperand = null
                    }
                    else -> {
                        if (displayText == "0") {
                            displayText = label
                        } else {
                            displayText += label
                        }
                    }
                }
            })
        }
    }
}

@Composable
fun DisplayScreen(displayText: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(Color.DarkGray, RoundedCornerShape(8.dp))
            .padding(16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        BasicText(
            text = displayText,
            style = TextStyle(
                color = Color.White,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun ButtonGrid(onButtonClick: (String) -> Unit) {
    val buttons = listOf(
        listOf("7", "8", "9", "/"),
        listOf("4", "5", "6", "*"),
        listOf("1", "2", "3", "-"),
        listOf("C", "0", "=", "+")
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        buttons.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                row.forEach { label ->
                    CalculatorButton(label = label, onClick = { onButtonClick(label) })
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(label: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .width(70.dp)  // Ensures each button takes up equal width
            .aspectRatio(1f)  // Keeps the button square
            .background(
                color = Color(0xFFFD9038),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onClick() },  // Handle click on button
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = TextStyle(
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}
