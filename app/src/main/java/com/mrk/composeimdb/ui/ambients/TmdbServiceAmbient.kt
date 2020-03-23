package com.mrk.composeimdb.ui.ambients

import androidx.compose.ambientOf
import com.mrk.composeimdb.repositories.network.TmdbService

val TmdbServiceAmbient = ambientOf<TmdbService> { error("TmdbServiceAmbient not initialised") }