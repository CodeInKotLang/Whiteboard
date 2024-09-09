package com.example.whiteboard.presentation.whiteboard

import androidx.compose.ui.geometry.Offset
import com.example.whiteboard.domain.model.DrawingTool
import com.example.whiteboard.domain.model.DrawnPath

data class WhiteboardState(
    val paths: List<DrawnPath> = emptyList(),
    val currentPath: DrawnPath? = null,
    val startingOffset: Offset = Offset.Zero,
    val selectedTool: DrawingTool = DrawingTool.PEN,
    val isDrawingToolsCardVisible: Boolean = false
)
