package com.mrk.composeimdb.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Clickable
import androidx.ui.graphics.Color
import androidx.ui.graphics.vector.drawVector
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutSize
import androidx.ui.material.ripple.Ripple
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import com.mrk.composeimdb.R

@Composable
fun VectorImageButton(@DrawableRes id: Int, onClick: () -> Unit) {
    Ripple(bounded = false) {
        Clickable(onClick = onClick) {
            VectorImage(id = id)
        }
    }
}

@Composable
fun VectorImage(modifier: Modifier = Modifier.None, @DrawableRes id: Int, tint: Color = Color.Transparent) {
    val vector = vectorResource(id)
    Container(
        modifier = modifier + LayoutSize(vector.defaultWidth, vector.defaultHeight)
    ) {
        drawVector(vector, tint)
    }
}

@Preview
@Composable
fun previewVectorImageButton() {
    val vector = vectorResource(R.drawable.ic_search)
    drawVector(vector)
}