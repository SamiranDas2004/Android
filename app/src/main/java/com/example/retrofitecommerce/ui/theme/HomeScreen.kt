package com.example.retrofitecommerce.ui.theme
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.retrofitecommerce.R
import com.plcoding.meditationuiyoutube.ui.theme.ButtonBlue
import com.plcoding.meditationuiyoutube.ui.theme.DarkerButtonBlue
import com.plcoding.meditationuiyoutube.ui.theme.DeepBlue
import com.plcoding.meditationuiyoutube.ui.theme.LightRed
import com.plcoding.meditationuiyoutube.ui.theme.TextWhite

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) { // Use weight for proper layout distribution
                Grettings(name = "Sam")
                ChipSection(chip = listOf("Sweet Dreams", "Sleep", "Eat"))
                CurrentMeditation()
                val features = listOf(
                    Feature("Feature 1", R.drawable.ic_headphone, Color(0xFF81C784), Color(0xFF388E3C)),
                    Feature("Feature 2", R.drawable.ic_headphone, Color(0xFF64B5F6), Color(0xFF1976D2)),
                    Feature("Feature 3", R.drawable.ic_headphone, Color(0xFFFF8A65), Color(0xFFD84315)),
                    Feature("Feature 4", R.drawable.ic_headphone, Color(0xFFBA68C8), Color(0xFF6A1B9A))
                )
                FeatureSection(features = features)
            }
            Footer(
                items = listOf(
                    Items(R.drawable.ic_music, Color(0xFF81C784), Color.White),
                    Items(R.drawable.ic_moon, Color(0xFF64B5F6), Color.White),
                    Items(R.drawable.ic_profile, Color(0xFFFF8A65), Color.White),
                    Items(R.drawable.ic_videocam, Color(0xFFBA68C8), Color.White)
                )
            )
        }
    }
}

@Composable
fun Grettings(name:String){
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(15.dp)
    ){
        Column (verticalArrangement = Arrangement.Center){
            Text(
                text = "Good Morning, $name",
                fontSize = 28.sp, // H2 size
                fontWeight = FontWeight.Bold, // H2-like weight
                color = Color.White,
                textAlign = TextAlign.Center // Optional: Center-align the text
            )
            Text(
                text = "Wish you have a good day",
                fontSize = 18.sp, // Adjust size for secondary text
                color = Color.White,
                textAlign = TextAlign.Center // Optional: Center-align the text
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_search), // Reference to ic_search.xml
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(24.dp) // Adjust the size if needed
        )
    }
}

@Composable
fun ChipSection(
    chip:List<String>
){
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }

    LazyRow {
        items(chip.size){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                .clickable {
                    selectedChipIndex=it
                }
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if (selectedChipIndex==it) ButtonBlue else DarkerButtonBlue
                )
                .padding(15.dp)
            ){
                Text(text = chip[it], color = Color.White)
            }
        }
    }
}

@Composable
fun CurrentMeditation(
    color:Color= LightRed
){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(15.dp).clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ){
        Column (){
            Text(
                text = "Daily Thought",
                fontSize = 28.sp, // H2 size
                fontWeight = FontWeight.Bold, // H2-like weight
                color = Color.White,
                textAlign = TextAlign.Center // Optional: Center-align the text
            )
            Text(
                text = "Meditation * 3-10 min",
                fontSize = 18.sp, // Adjust size for secondary text
                color = TextWhite,
                textAlign = TextAlign.Center // Optional: Center-align the text
            )
        }
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier.size(30.dp).clip(CircleShape)
                .background(ButtonBlue).padding(10.dp)
            ){
            Icon(
                painter = painterResource(id=R.drawable.ic_play),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

data class Feature(
    val title: String,
    @DrawableRes val iconId: Int,
    val mediumColor: Color,
    val darkColor: Color
)

@Composable
fun FeatureSection(features: List<Feature>) {
    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
             // Allow the FeatureSection to take up remaining space
    ) {
        Text(
            text = "Features",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
//            modifier = Modifier.padding(15.dp),
            color = Color.White
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.dp, end = 7.dp, bottom = 8.dp), // Reduce bottom padding
//            modifier = Modifier.fillMaxHeight(0.8f) // Constrain height to 80% of available space
        ) {
            items(features) { feature ->
                FeatureItem(feature = feature)
            }
        }
    }
}

@Composable
fun FeatureItem(feature: Feature) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .aspectRatio(1f) // Make it a square
            .background(
                color = feature.mediumColor, // Use mediumColor as background
                shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = feature.iconId),
                contentDescription = feature.title,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(40.dp) // Icon size
                    .padding(bottom = 8.dp)
            )
            Text(
                text = feature.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White // Set text color to white for better visibility
            )
        }
    }
}
data class Items(
    val iconId: Int,
    val backgroundColor: Color,
    val iconColor: Color
)

@Composable
fun Footer(items: List<Items>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), // Padding around the footer
        horizontalArrangement = Arrangement.SpaceBetween, // Space between each item
    ) {
        items.forEach { item ->
            Box(
                modifier = Modifier
                    .size(60.dp), // Box size
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(id = item.iconId),
                    contentDescription = null, // Icon description
                    tint = Color.White, // Apply the color to the icon
                    modifier = Modifier.size(24.dp) // Icon size
                )
            }
        }
    }
}

