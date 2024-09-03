package com.example.whiteboard.domain.model

import com.example.whiteboard.R

enum class DrawingTool(
    val resId: Int
) {
    PEN(resId = R.drawable.img_pen),
    HIGHLIGHTER(resId = R.drawable.img_highlighter),
    LASER_PEN(resId = R.drawable.img_laser_pen),
    ERASER(resId = R.drawable.img_eraser),
    LINE_PLAIN(resId = R.drawable.ic_line_plain),
    LINE_DOTTED(resId = R.drawable.ic_line_dotted),
    ARROW_ONE_SIDED(resId = R.drawable.ic_arrow_one_sided),
    ARROW_TWO_SIDED(resId = R.drawable.ic_arrow_two_sided),
    RECTANGLE_OUTLINE(resId = R.drawable.ic_rectangle_outline),
    CIRCLE_OUTLINE(resId = R.drawable.ic_circle_outline),
    TRIANGLE_OUTLINE(resId = R.drawable.ic_triangle_outline),
    RECTANGLE_FILLED(resId = R.drawable.ic_rectangle_filled),
    CIRCLE_FILLED(resId = R.drawable.ic_circle_filled),
    TRIANGLE_FILLED(resId = R.drawable.ic_triangle_filled),
}