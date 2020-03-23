package com.mrk.composeimdb.ui.common

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.Composable
import androidx.compose.onCommit
import androidx.compose.state
import androidx.ui.core.Text
import androidx.ui.graphics.asImageAsset
import androidx.ui.layout.Container
import androidx.ui.unit.Dp
import com.mrk.composeimdb.ambients.TmdbConfigurationAmbient
import com.squareup.picasso.Picasso

@Composable
fun ImageNetwork(url: String?, width: Dp, height: Dp) {
    val configuration = TmdbConfigurationAmbient.current
    val image = state<Bitmap?> { null }

    onCommit(url) {
        val picasso = Picasso.get()
        val target = object : com.squareup.picasso.Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                image.value = bitmap
            }
        }
        picasso
            .load(configuration.images.imageBaseUrl + configuration.images.posterSizes[0] + url)
            .into(target)

        onDispose {
            image.value = null
            picasso.cancelRequest(target)
        }
    }

    Container(height = height, width = width) {
        if (image.value != null) {
            androidx.ui.foundation.Image(image = image.value!!.asImageAsset())
        } else {
            Text("loading...")
        }
    }
}
