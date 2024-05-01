package com.chorokbul.map.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.chorokbul.resource.R

@Composable
fun CustomFabButton(
    modifier: Modifier = Modifier,
    image: Painter,
    onClick: () -> Unit
) {
    Image(
        painter = image,
        contentDescription = "Custom Fab Button",
        colorFilter = ColorFilter.tint(colorResource(id = R.color.green)),
        modifier = modifier
            .shadow(
                elevation = 5.dp,
                shape = CircleShape
            )
            .background(
                color = Color.White,
                shape = CircleShape,
            )
            .clickable {
                onClick()
            }
            .padding(12.dp),
    )
}