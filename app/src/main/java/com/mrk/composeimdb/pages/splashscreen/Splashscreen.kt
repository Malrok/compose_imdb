package com.mrk.composeimdb.pages.splashscreen

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Image
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.preferredHeight
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.res.vectorResource
import androidx.ui.unit.dp
import com.mrk.composeimdb.R

@Composable
fun Splashscreen() {
    Surface(
        color = MaterialTheme.colors.secondary,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.preferredHeight(64.dp),
            asset = vectorResource(id = R.drawable.ic_imdb)
        )
    }
}