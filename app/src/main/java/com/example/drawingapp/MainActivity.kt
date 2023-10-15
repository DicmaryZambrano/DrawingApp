package com.example.drawingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.drawingapp.ui.theme.DrawingAppTheme
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.*
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrawingAppTheme {
                DrawingApp()
            }
        }
    }
}

@Composable
fun DrawingScreen(modifier: Modifier) {
    val lines = remember {
        mutableStateListOf<Line>()
    }

    Column(Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .height(130.dp)
                .fillMaxWidth()
                .background(Color(0xFFE5E7E6))
        ){
            Row (modifier = Modifier
                .fillMaxSize()) {
                CanvasTools(Modifier.weight(1f))
                ColorPicker(Modifier.weight(1f))
            }
        }
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFFFFF))
        ) {
        }
    }
}

@Composable
fun CanvasTools(modifier: Modifier = Modifier) {
    Column (
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.brush_icon),
                contentDescription = stringResource(R.string.brush),
                modifier = Modifier.clickable {println("Brush")}
            )
            Image(
                painter = painterResource(id = R.drawable.baseline_download_for_offline_24),
                contentDescription = stringResource(R.string.download),
                modifier = Modifier.clickable {println("Download")}
            )
            Image(
                painter = painterResource(id = R.drawable.eraser_icon),
                contentDescription = stringResource(R.string.eraser),
                modifier = Modifier.clickable {println("Eraser")}
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.square_icon),
                contentDescription = stringResource(R.string.square),
                modifier = Modifier.clickable {println("Square")}
            )
            Image(
                painter = painterResource(id = R.drawable.triangle_icon),
                contentDescription = stringResource(R.string.triangle),
                modifier = Modifier.clickable {println("Triangle")}
            )
            Image(
                painter = painterResource(id = R.drawable.circle_icon),
                contentDescription = stringResource(R.string.circle),
                modifier = Modifier.clickable {println("Circle")}
            )
        }
    }
}

@Preview
@Composable
fun ColorPicker(modifier: Modifier = Modifier){
    Column (
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ColorIcon(modifier = Modifier.clickable { println("Black") }, color = Color(0xFF000000))
            ColorIcon(modifier = Modifier.clickable { println("Gray") }, color = Color(0xFF7F7F7F))
            ColorIcon(modifier = Modifier.clickable { println("Red") }, color = Color(0xFFED1C24))
        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ColorIcon(modifier = Modifier.clickable { println("Orange") }, color = Color(0xFFFF7F27))
            ColorIcon(modifier = Modifier.clickable { println("Blue") }, color = Color(0xFF009FE4))
            ColorIcon(modifier = Modifier.clickable { println("Purple") }, color = Color(0xFFA449A5))
        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ColorIcon(modifier = Modifier.clickable { println("Yellow") }, color = Color(0xFFFFF430))
            ColorIcon(modifier = Modifier.clickable { println("Green") }, color = Color(0xFF24B24E))
            ColorIcon(modifier = Modifier.clickable { println("White") }, color = Color(0xFFFFFFFF))
        }
    }
}

@Composable
fun ColorIcon(modifier: Modifier= Modifier, color: Color){
    Canvas(
        modifier = Modifier
            .size(30.dp)
            .background(Color.Black)
            .padding(3.dp)
    ) {
        drawRect(
            color = color
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DrawingApp() {
    DrawingAppTheme {
        DrawingScreen(modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
        )
    }
}

data class Line(
    val start: Offset,
    val end: Offset,
    val color: Color = Color.Black,
    val strokeWidth: Dp = 1.dp
)

enum class Shape {
    Line,
    Circle,
    Square,
    Triangle
}