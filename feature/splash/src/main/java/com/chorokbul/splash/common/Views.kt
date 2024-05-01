package com.chorokbul.splash.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp

@Composable
internal fun EmptyColorBox(
    height: Dp,
    colorRes: Int,
    modifier: Modifier = Modifier
) {
    Spacer(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(
                color = colorResource(id = colorRes),
                shape = RectangleShape
            )
    )
}
