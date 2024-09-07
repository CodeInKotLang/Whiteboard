package com.example.whiteboard.presentation.whiteboard

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.whiteboard.domain.model.DrawingTool
import com.example.whiteboard.presentation.whiteboard.component.DrawingToolFAB
import com.example.whiteboard.presentation.whiteboard.component.DrawingToolsCard
import com.example.whiteboard.presentation.whiteboard.component.TopBar

@Composable
fun WhiteboardScreen(
    modifier: Modifier = Modifier
) {

    var isDrawingToolsCardVisible by rememberSaveable { mutableStateOf(false) }
    var selectedDrawingTool by remember { mutableStateOf(DrawingTool.PEN) }


    Box(
        modifier = modifier.fillMaxSize()
    ) {
        DrawingCanvas(
            modifier = Modifier.fillMaxSize()
        )
        TopBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(20.dp),
            onHomeIconClick = {

            },
            onUndoIconClick = { },
            onRedoIconClick = { },
            onStrokeWidthClick = { },
            onDrawingColorClick = { },
            onBackgroundColorClick = { },
            onSettingsClick = {}
        )
        DrawingToolFAB(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
            isVisible = !isDrawingToolsCardVisible,
            selectedTool = selectedDrawingTool,
            onClick = { isDrawingToolsCardVisible = true }
        )
        DrawingToolsCard(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp),
            isVisible = isDrawingToolsCardVisible,
            selectedTool = selectedDrawingTool,
            onToolClick = { selectedDrawingTool = it },
            onCloseIconClick = { isDrawingToolsCardVisible = false }
        )
    }
}

@Composable
private fun DrawingCanvas(
    modifier: Modifier = Modifier
) {

    var paths by remember { mutableStateOf(listOf<Path>()) }
    var currentPath by remember { mutableStateOf<Path?>(null) }
    var startingOffset by remember { mutableStateOf(Offset.Zero) }

    Canvas(
        modifier = modifier
            .background(Color.White)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        startingOffset = offset
                    },
                    onDrag = { change, _ ->
                        val offset = Offset(x = change.position.x, y = change.position.y)
                        val existingPath = currentPath ?: Path().apply {
                            moveTo(startingOffset.x, startingOffset.y)
                        }
                        currentPath = Path().apply {
                            addPath(existingPath)
                            lineTo(offset.x, offset.y)
                        }
                    },
                    onDragEnd = {
                        val newPath = currentPath ?: Path()
                        paths = paths + newPath
                        currentPath = null
                    }
                )
            }
    ) {
        paths.forEach { path ->
            drawPath(
                path = path,
                color = Color.Black,
                style = Stroke(width = 10f)
            )
        }

        currentPath?.let {
            drawPath(
                path = it,
                color = Color.Black,
                style = Stroke(width = 10f)
            )
        }

        /*drawPath(
            path = createPath(),
            color = Color.Black,
            style = Stroke(width = 10f)
        )
        drawPath(
            path = createRectPath(),
            color = Color.Black,
            style = Fill
        )
        drawPath(
            path = createQuadCurvePath(),
            color = Color.Black,
            style = Stroke(width = 10f)
        )
        drawPath(
            path = createCubicCurvePath(),
            color = Color.Red,
            style = Fill
        )
        drawCircle(
            color = Color.Blue,
            radius = 300f,
            center = center
        )
        drawLine(
            color = Color.Black,
            start = Offset(150f, 750f),
            end = Offset(150f, 1200f),
            strokeWidth = 10f
        )
        drawRect(
            color = Color.Black,
            topLeft = Offset(500f, 750f),
            size = size / 2f
        )*/
    }
}

private fun createQuadCurvePath(): Path {
    return Path().apply {
        moveTo(100f, 1000f)
        quadraticBezierTo(500f, 600f, 800f, 1000f)
    }
}

private fun createCubicCurvePath(): Path {
    return Path().apply {
        moveTo(100f, 1300f)
        cubicTo(100f, 700f, 800f, 700f, 800f, 1300f)
        close()
    }
}

private fun createPath(): Path {
    return Path().apply {
        moveTo(100f, 100f)
        lineTo(500f, 800f)
        lineTo(1000f, 100f)
        close()
    }
}

private fun createRectPath(): Path {
    return Path().apply {
        moveTo(500f, 1500f)
        lineTo(500f, 2000f)
        lineTo(1000f, 2000f)
        lineTo(1000f, 1500f)
        close()
    }
}

@Preview
@Composable
private fun DrawingCanvasPreview() {
    DrawingCanvas(
        modifier = Modifier.fillMaxSize()
    )
}