package com.mrk.composeimdb.ambients

import androidx.compose.ambientOf
import com.mrk.composeimdb.repositories.TmdbService

val TmdbServiceAmbient = ambientOf<TmdbService> { error("TmdbServiceAmbient not initialised") }