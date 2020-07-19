package com.mrk.composeimdb.components

import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.Icon
import androidx.ui.foundation.clickable
import androidx.ui.material.MaterialTheme
import androidx.ui.res.vectorResource

@Composable
fun IconButton(@DrawableRes id: Int, onClick: () -> Unit) {
    Box(Modifier
        .clickable(onClick = onClick), children = {
            Icon(
                tint = MaterialTheme.colors.secondary,
                asset = vectorResource(id = id)
            )
        })
}
