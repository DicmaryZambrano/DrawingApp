package com.example.drawingapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.drawingapp.ui.theme.DrawingAppTheme

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
fun DrawingScreen(modifier: Modifier,
                  lines: MutableList<Line>,
                  selectedColor: MutableState<Color>,
                  selectedStrokeWidth: MutableState<Dp>) {
    //The main drawing screen of the app. There is a tool bar and a color picker

    Column(Modifier.fillMaxSize()) {
        //The header of the app
        Box(
            modifier = Modifier
                .height(130.dp)
                .fillMaxWidth()
                .background(Color(0xFFE5E7E6))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CanvasTools(Modifier.weight(1f),selectedStrokeWidth,lines)
                ColorPicker(Modifier.weight(1f),selectedColor)
            }
        }
        //The main canvas, here is where all the drawing is done
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFFFFF))
                .pointerInput(key1 = true) {
                    //The gesture is detected and the information is used to create the lines and add them to a list of line objects
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        val line = Line(
                            start = change.position - dragAmount, //the start of the line is calculated 
                            end = change.position,// the end of the line is calculated
                            color = selectedColor.value, // the color of the line is set to the current selected color
                            strokeWidth = selectedStrokeWidth.value // the width of the line is set to the current selected width
                        )
                        lines.add(line)
                    }
                }
        ) {
            //The lines from the line object lists are drawn into the canvas
            lines.forEach { line ->
                drawLine(
                    color = line.color,
                    start = line.start,
                    end = line.end,
                    strokeWidth = line.strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            }
        }
    }
}


@Composable
fun CanvasTools(modifier: Modifier = Modifier,selectedStrokeWidth: MutableState<Dp>,lines: MutableList<Line>) {
    //Defines the tool bar and takes care of changing the
    Column (
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                //This image can be selected so that the canvas is restored
                painter = painterResource(id = R.drawable.eraser_icon),
                contentDescription = stringResource(R.string.eraser),
                modifier = Modifier
                    .clickable { lines.clear() }
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                //Once this image is clicked the selectedStrokeWidth will be updated to a small stroke
                painter = painterResource(id = R.drawable.stroke_small_icon),
                contentDescription = stringResource(R.string.stroke_small),
                modifier = Modifier
                    .clickable { selectedStrokeWidth.value = 1.dp }
                    .weight(1f)
            )
            Image(
                //Once this image is clicked the selectedStrokeWidth will be updated to a medium stroke
                painter = painterResource(id = R.drawable.stroke_medium_icon),
                contentDescription = stringResource(R.string.stroke_medium),
                modifier = Modifier
                    .clickable { selectedStrokeWidth.value = 5.dp }
                    .weight(1f)
            )
            Image(
                //Once this image is clicked the selectedStrokeWidth will be updated to a large stroke
                painter = painterResource(id = R.drawable.stroke_big_icon),
                contentDescription = stringResource(R.string.stroke_big),
                modifier = Modifier
                    .clickable { selectedStrokeWidth.value = 10.dp }
                    .weight(1f)
            )
        }
    }
}

@Composable
fun ColorPicker(modifier: Modifier = Modifier,selectedColor: MutableState<Color>){
    //Layout for the color icons
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
            Box(
                //Each icon has a clickable box that sets the selectedColor to their corresponding color
                modifier = Modifier
                    .clickable { selectedColor.value = Color(0xFF000000) }
            ) {
                ColorIcon(
                    modifier = Modifier,
                    color = Color(0xFF000000)
                )
            }
            Box(
                modifier = Modifier
                    .clickable { selectedColor.value = Color(0xFF7F7F7F) }
            ) {
                ColorIcon(
                    modifier = Modifier,
                    color = Color(0xFF7F7F7F)
                )
            }
            Box(
                modifier = Modifier
                    .clickable { selectedColor.value = Color(0xFFED1C24) }
            ) {
                ColorIcon(
                    modifier = Modifier,
                    color = Color(0xFFED1C24)
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clickable { selectedColor.value = Color(0xFFFF7F27) }
            ) {
                ColorIcon(
                    modifier = Modifier,
                    color = Color(0xFFFF7F27)
                )
            }
            Box(
                modifier = Modifier
                    .clickable { selectedColor.value = Color(0xFF009FE4) }
            ) {
                ColorIcon(
                    modifier = Modifier,
                    color = Color(0xFF009FE4)
                )
            }
            Box(
                modifier = Modifier
                    .clickable { selectedColor.value = Color(0xFFA449A5) }
            ) {
                ColorIcon(
                    modifier = Modifier,
                    color = Color(0xFFA449A5)
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clickable { selectedColor.value = Color(0xFFFFF430) }
            ) {
                ColorIcon(
                    modifier = Modifier,
                    color = Color(0xFFFFF430)
                )
            }
            Box(
                modifier = Modifier
                    .clickable { selectedColor.value = Color(0xFF24B24E) }
            ) {
                ColorIcon(
                    modifier = Modifier,
                    color = Color(0xFF24B24E)
                )
            }
            Box(
                modifier = Modifier
                    .clickable { selectedColor.value = Color(0xFFFFFFFF) }
            ) {
                ColorIcon(
                    modifier = Modifier,
                    color = Color(0xFFFFFFFF)
                )
            }
        }
    }
}

@Composable
fun ColorIcon(modifier: Modifier = Modifier, color: Color) {
    //A simple icon that can be reused
    Box(
        modifier = modifier
            .size(30.dp)
            .padding(3.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color)
                .border(2.dp, Color.Black)
        )
    }
}
/*
private fun saveDrawingList() {
    val sharedPreferences = getSharedPreferences("MyDrawingApp", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    val gson = Gson()
    val json = gson.toJson(lines)
    editor.putString("drawingList", json)
    editor.apply()
}
*/
@Preview(showBackground = true)
@Composable
fun DrawingApp() {
    //remembers the state that the user has selected. And set the default values of the lines
    val lines = remember { mutableStateListOf<Line>() }
    val selectedColor = remember { mutableStateOf(Color.Black) }
    val selectedStrokeWidth = remember { mutableStateOf(1.dp) }

    DrawingScreen(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center),
        lines = lines,
        selectedColor = selectedColor,
        selectedStrokeWidth = selectedStrokeWidth
    )
}

//Represents a line on the canvas
data class Line(
    val start: Offset,
    val end: Offset,
    val color: Color = Color.Black,
    val strokeWidth: Dp = 1.dp
)