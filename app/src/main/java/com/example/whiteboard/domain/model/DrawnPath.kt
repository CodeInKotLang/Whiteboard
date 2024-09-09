package com.example.whiteboard.domain.model

import androidx.compose.ui.graphics.Path

data class DrawnPath(
    val path: Path,
    val drawingTool: DrawingTool
)