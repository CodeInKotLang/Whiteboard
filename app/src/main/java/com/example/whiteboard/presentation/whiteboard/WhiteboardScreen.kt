package com.example.whiteboard.presentation.whiteboard

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
        TopBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(20.dp),
            onHomeIconClick = { },
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